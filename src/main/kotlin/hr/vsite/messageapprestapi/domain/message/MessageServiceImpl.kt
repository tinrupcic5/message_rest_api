package hr.vsite.messageapprestapi.domain.message

import hr.vsite.messageapprestapi.domain.message.adapter.incoming.web.MessageRequest
import hr.vsite.messageapprestapi.domain.message.adapter.outgoing.jpa.MessageRepository
import hr.vsite.messageapprestapi.domain.message.adapter.outgoing.jpa.requestToDomain
import hr.vsite.messageapprestapi.domain.message.adapter.outgoing.jpa.toDto
import hr.vsite.messageapprestapi.domain.message.adapter.outgoing.web.MessageDto
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.UserRepository
import hr.vsite.messageapprestapi.firebase.FirebaseMessagingService
import hr.vsite.messageapprestapi.firebase.FirebaseService
import hr.vsite.messageapprestapi.firebase.NotificationMessage
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Service
@Validated
class MessageServiceImpl(
    private val messageRepository: MessageRepository,
    private val userRepository: UserRepository,
    private val firebaseMessagingService: FirebaseMessagingService,
    private val firebaseService: FirebaseService,
) : MessageService {

    override fun findMessagesByChatId(chatId: Int): Set<MessageDto> =
        messageRepository.findMessagesByChatId(chatId).map { it.toDto() }.toSet()

    override fun deleteMessageById(id: Int): String {
        val exist = messageRepository.existsById(id)
        if (!exist) {
            return "Message not deleted"
        } else {
            messageRepository.deleteMessageById(id)
        }
        return "Message deleted"
    }

    override fun sendMessage(message: MessageRequest): String {
        val fire = firebaseService.findFirebaseTokenByChatId(message.chat.chatId!!, message.userSender.userId!!)

        if (message.messageText.isEmpty()) {
            return "Message is empty"
        } else {
            messageRepository.sendMessage(message.requestToDomain())

            firebaseMessagingService.sendNotificationByToken(
                NotificationMessage(
                    recepientToken = fire.firebaseToken,
                    title = "MESSAGE",
                    body = "From ${message.userSender.userName}",
                    data = mapOf(
                        "chatId" to message.chat.chatId.toString(),
                    ),
                ),
            )
            return "Message sent"
        }
    }

    override fun readMessage(chatId: Int, userId: Int): Boolean {
        val u = userRepository.findById(userId.toString())
        val fire = firebaseService.findFirebaseTokenByChatId(chatId, userId)


        firebaseMessagingService.sendNotificationByToken(
            NotificationMessage(
                recepientToken = fire.firebaseToken,
                title = "CHAT",
                body = "From ${u.get().userName}",
                data = emptyMap(),
            ),
        )
        return messageRepository.readMessage(chatId, userId)
    }
}
