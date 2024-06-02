package hr.vsite.messageapprestapi.domain.participants.adapter.incoming.web

import hr.vsite.messageapprestapi.domain.chat.adapter.incoming.web.ChatRequest
import hr.vsite.messageapprestapi.domain.user.adapter.incoming.web.UserRequest

class ParticipantsRequest(
    val chat: ChatRequest,
    val user: UserRequest,
)
