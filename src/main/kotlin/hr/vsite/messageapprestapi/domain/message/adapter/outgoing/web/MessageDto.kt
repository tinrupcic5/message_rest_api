package hr.vsite.messageapprestapi.domain.message.adapter.outgoing.web

import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.web.chat.ChatDto
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.web.user.UserDto

class MessageDto(
    val messageId: Int?,
    val messageText: String,
    val createdAt: String,
    val chat: ChatDto,
    val userSender: UserDto,
    val isRead: Boolean
)
