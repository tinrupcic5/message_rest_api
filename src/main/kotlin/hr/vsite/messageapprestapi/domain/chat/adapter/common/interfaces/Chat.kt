package hr.vsite.messageapprestapi.domain.chat.adapter.common.interfaces

import hr.vsite.messageapprestapi.domain.user.common.interfaces.User
import java.time.LocalDateTime

data class Chat(
    val chatId: ChatId?,
    val chatName: ChatName,
    val createdAt: LocalDateTime,
    val user: User,
) {
    override fun toString(): String {
        return "Chat(chatId=$chatId, chatName=$chatName, createdAt=$createdAt, user=$user)"
    }
}
