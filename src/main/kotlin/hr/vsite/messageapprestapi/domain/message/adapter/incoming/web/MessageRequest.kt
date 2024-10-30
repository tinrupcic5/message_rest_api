package hr.vsite.messageapprestapi.domain.message.adapter.incoming.web

import hr.vsite.messageapprestapi.domain.chat.adapter.incoming.web.ChatRequest
import hr.vsite.messageapprestapi.domain.user.adapter.incoming.web.UserRequest

class MessageRequest(
    val messageText: String,
    val chat: ChatRequest,
    val userSender: UserRequest,
)
