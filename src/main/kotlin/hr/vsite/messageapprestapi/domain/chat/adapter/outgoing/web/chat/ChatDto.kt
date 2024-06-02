package hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.web.chat

import hr.vsite.messageapprestapi.domain.message.adapter.outgoing.web.MessageDto
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.web.user.UserDto
import java.time.LocalDateTime

data class ChatDto(
    val chatId: Int?,
    val chatName: String,
    val createdAt: LocalDateTime,
    val userCreator: UserDto,
    val messageDto: List<MessageDto>? = null
)
