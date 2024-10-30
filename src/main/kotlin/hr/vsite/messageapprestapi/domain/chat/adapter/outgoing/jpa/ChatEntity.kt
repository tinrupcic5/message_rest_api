package hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa

import hr.vsite.messageapprestapi.domain.chat.adapter.common.interfaces.Chat
import hr.vsite.messageapprestapi.domain.chat.adapter.common.interfaces.ChatId
import hr.vsite.messageapprestapi.domain.chat.adapter.common.interfaces.ChatName
import hr.vsite.messageapprestapi.domain.chat.adapter.incoming.web.ChatRequest
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.web.chat.ChatDto
import hr.vsite.messageapprestapi.domain.message.adapter.outgoing.web.MessageDto
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.UserEntity
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.entityToDto
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.requestToDomain
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.toDomain
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.toDto
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.toEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "chats")
data class ChatEntity(
    @Id
    @Column(name = "chat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val chatId: Int?,

    @Column(name = "chat_name")
    val chatName: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserEntity,
)

fun Set<ChatEntity>.setToDomain(): Set<Chat> {
    return this.map { chatEntity ->
        Chat(
            chatId = chatEntity.chatId?.let { ChatId(it) },
            chatName = ChatName(chatEntity.chatName),
            createdAt = chatEntity.createdAt,
            user = chatEntity.user.toDomain(),
        )
    }.toSet()
}

fun Set<Chat>.setToDto(): Set<ChatDto> {
    return this.map { chat ->
        ChatDto(
            chatId = chat.chatId?.value,
            chatName = chat.chatName.value,
            createdAt = chat.createdAt,
            userCreator = chat.user.toDto(),
        )
    }.toSet()
}

fun ChatEntity.toDomain(): Chat =
    Chat(
        chatId = ChatId(this.chatId!!),
        chatName = ChatName(this.chatName),
        createdAt = this.createdAt,
        user = this.user.toDomain(),
    )

fun ChatEntity.entityToDto(): ChatDto =
    ChatDto(
        chatId = this.chatId,
        chatName = this.chatName,
        createdAt = this.createdAt,
        userCreator = user.entityToDto(),
    )

fun Chat.toEntity(): ChatEntity =
    ChatEntity(
        chatId = this.chatId?.value,
        chatName = this.chatName.value,
        createdAt = LocalDateTime.now(),
        user = this.user.toEntity(),
    )

fun Chat.toDto(message: List<MessageDto>? = null) = ChatDto(
    chatId = this.chatId?.value,
    chatName = this.chatName.value,
    createdAt = this.createdAt,
    userCreator = user.toDto(),
    messageDto = message
)

fun ChatDto.toDomain(): Chat =
    Chat(
        chatId = ChatId(this.chatId!!),
        chatName = ChatName(this.chatName),
        createdAt = this.createdAt,
        user = this.userCreator.toDomain(),
    )

fun ChatRequest.requestToDomain(): Chat =
    Chat(
        chatId = ChatId(this.chatId),
        chatName = ChatName(this.chatName),
        createdAt = LocalDateTime.now(),
        user = this.userCreator.requestToDomain(),
    )
