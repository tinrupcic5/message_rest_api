package hr.vsite.messageapprestapi.domain.message.adapter.common.interfaces

import hr.vsite.messageapprestapi.domain.chat.adapter.common.interfaces.Chat
import hr.vsite.messageapprestapi.domain.user.common.interfaces.User
import java.time.LocalDateTime

data class Message(
    val messageId: MessageId?,
    val messageText: MessageText,
    val createdAt: LocalDateTime,
    val chat: Chat,
    val user: User,
    val isRead: Boolean
) {
    override fun toString(): String {
        return "Message(messageId='$messageId', messageText='$messageText', " +
            "createdAt=$createdAt, chat=$chat, user=$user)"
    }
}
