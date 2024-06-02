package hr.vsite.messageapprestapi.domain.chat

import hr.vsite.messageapprestapi.domain.chat.adapter.common.interfaces.Chat
import hr.vsite.messageapprestapi.domain.chat.adapter.incoming.web.ChatRequest
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa.ChatRepository
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa.entityToDto
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa.requestToDomain
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa.setToDto
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa.toDomain
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa.toDto
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.web.chat.ChatDto
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.web.chat.UserChatDto
import hr.vsite.messageapprestapi.domain.message.adapter.outgoing.jpa.MessageRepository
import hr.vsite.messageapprestapi.domain.message.adapter.outgoing.jpa.toDto
import hr.vsite.messageapprestapi.domain.participants.adapter.incoming.web.ParticipantsRequest
import hr.vsite.messageapprestapi.domain.participants.adapter.outgoing.jpa.ParticipantsRepository
import hr.vsite.messageapprestapi.domain.participants.adapter.outgoing.jpa.requestToDomain
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.UserRepository
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.setToDto
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.web.user.UserDto
import hr.vsite.messageapprestapi.domain.user.common.interfaces.User
import hr.vsite.messageapprestapi.domain.user.common.interfaces.UserId
import hr.vsite.messageapprestapi.errorhandling.DomainNotFoundException
import hr.vsite.messageapprestapi.firebase.FirebaseMessagingService
import hr.vsite.messageapprestapi.firebase.FirebaseService
import hr.vsite.messageapprestapi.firebase.NotificationMessage
import mu.KotlinLogging
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Service
@Validated
class ChatServiceImpl(
    val chatRepository: ChatRepository,
    val participantsRepository: ParticipantsRepository,
    val firebaseMessagingService: FirebaseMessagingService,
    val firebaseService: FirebaseService,
    val userRepository: UserRepository,
    val messageRepository: MessageRepository,
) : ChatService {
    private val logger = KotlinLogging.logger { }

    override fun findById(id: String): ChatDto =
        chatRepository.findById(id).toDto()


    override fun findChatByUserOrByMessageUserId(userId: Int): Set<UserChatDto> {

        val userSet: Set<User> =
            userRepository.findChatByUserOrByMessageUserId(userId)
        val chatSet: Set<Chat> =
            chatRepository.findChatByUserOrByMessageUserId(userId)
        val userDtoSet: Set<UserDto> = userSet.setToDto()
        var chatDtoSet: MutableSet<ChatDto> = mutableSetOf() // Use MutableSet to allow addition of elements

        chatSet.forEach { chat ->
            val chatId = chat.chatId?.value ?: return@forEach  // Skip if chatId is null
            val messages = messageRepository.findMessagesByChatId(chatId).sortedByDescending { it.messageId }

            val messageDtoSet = messages.map { it.toDto() }

            chatDtoSet.add(chat.toDto(messageDtoSet))
        }


        val userChatDtoSet: MutableSet<UserChatDto> = mutableSetOf()
        for (i in userDtoSet.indices) {
            userChatDtoSet.add(UserChatDto(chatDtoSet.elementAt(i), userDtoSet.elementAt(i)))
        }

        return userChatDtoSet
    }

    override fun deleteChatById(id: String): String {
        val exist = chatRepository.existsById(id)
        if (!exist) {
            throw DomainNotFoundException("Chat with id: $id does not exists!")
        }
        return "Chat : ${chatRepository.deleteChatById(id)} has been deleted!"
    }

    // there is no group chats for now
    override fun create(chat: ChatRequest, participants: Set<ParticipantsRequest>): ChatDto {
        val firstUserId = participants.first().user.userId
        val secondUserId = participants.elementAt(1).user.userId
        val chatIdIfExists =
            participantsRepository.checkIfChatWithParticipantsExist(
                UserId(firstUserId!!),
                UserId(secondUserId!!),
            )
        if (chatIdIfExists.value != null) {
            for (part in participants) {
                logger.info("user: " + part.user.userName)
                val fire = firebaseService.findFirebaseTokenByUserId(part.user.userId!!)
                if (fire != null) {
                    firebaseMessagingService.sendNotificationByToken(
                        NotificationMessage(
                            recepientToken = fire.firebaseToken,
                            title = "CHAT",
                            body = "some body",
                            data = mapOf(
                                "userId" to participants.map { it.user.userId }.toString(),
                            ),
                        ),
                    )
                }
            }

            return chatRepository.findById(chatIdIfExists.value.toString()).toDto()
        }

        val chats = chatRepository.create(chat.requestToDomain())
        val participantsWithChat = participants.requestToDomain().map { it.copy(chat = chats.toDomain()) }.toSet()

        participantsRepository.insert(participantsWithChat)

        for (part in participantsWithChat) {
            logger.info("user: " + part.user.userName)
            val fire = firebaseService.findFirebaseTokenByUserId(part.user.userId!!.value)
            if (fire != null) {
                firebaseMessagingService.sendNotificationByToken(
                    NotificationMessage(
                        recepientToken = fire.firebaseToken,
                        title = "CHAT",
                        body = "some body",
                        data = mapOf(
                            "userId" to participants.map { it.user.userId }.toString(),
                        ),
                    ),
                )
            }
        }
        return chats.entityToDto()
    }
}
