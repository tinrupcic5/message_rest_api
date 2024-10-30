package hr.vsite.messageapprestapi.domain.message.adapter.outgoing.jpa

import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa.ChatEntity
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa.requestToDomain
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa.toDomain
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa.toDto
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa.toEntity
import hr.vsite.messageapprestapi.domain.message.adapter.common.interfaces.Message
import hr.vsite.messageapprestapi.domain.message.adapter.common.interfaces.MessageId
import hr.vsite.messageapprestapi.domain.message.adapter.common.interfaces.MessageText
import hr.vsite.messageapprestapi.domain.message.adapter.incoming.web.MessageRequest
import hr.vsite.messageapprestapi.domain.message.adapter.outgoing.web.MessageDto
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.UserEntity
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
@Table(name = "messages")
data class MessageEntity(
    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val messageId: Int?,

    @Column(name = "message_text")
    val messageText: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Column(name = "is_read")
    val isRead: Boolean,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    val chat: ChatEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserEntity,
)
fun MessageEntity.toDomain() =
    Message(
        messageId = MessageId(messageId!!),
        messageText = MessageText(messageText),
        createdAt = this.createdAt,
        chat = chat.toDomain(),
        user = user.toDomain(),
        isRead = this.isRead
    )
fun Message.toEntity() = MessageEntity(
    messageId = this.messageId?.value,
    messageText = this.messageText.value,
    createdAt = LocalDateTime.now(),
    chat = this.chat.toEntity(),
    user = this.user.toEntity(),
    isRead = this.isRead
)

fun Message.toDto() = MessageDto(
    messageId = this.messageId?.value,
    messageText = this.messageText.value,
    createdAt = this.createdAt.toString(),
    chat = this.chat.toDto(),
    userSender = this.user.toDto(),
    isRead = this.isRead
)

fun MessageDto.toDomain() =
    Message(
        messageId = MessageId(messageId!!),
        messageText = MessageText(messageText),
        createdAt = LocalDateTime.now(),
        chat = chat.toDomain(),
        user = userSender.toDomain(),
        isRead = this.isRead
    )

fun MessageRequest.requestToDomain() =
    Message(
        messageId = null,
        messageText = MessageText(messageText),
        createdAt = LocalDateTime.now(),
        chat = chat.requestToDomain(),
        user = userSender.requestToDomain(),
        isRead = false
    )
