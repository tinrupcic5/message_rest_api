package hr.vsite.messageapprestapi.domain.chat.adapter.incoming.web

import hr.vsite.messageapprestapi.domain.user.adapter.incoming.web.UserRequest

data class ChatRequest(
    val chatId: Int?,
    val chatName: String,
    val userCreator: UserRequest,
)
