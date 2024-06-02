package hr.vsite.messageapprestapi.domain.chat.adapter.incoming.web

import hr.vsite.messageapprestapi.domain.participants.adapter.incoming.web.ParticipantsRequest

class ChatParticipantsRequest(
    val chatRequest: ChatRequest,
    val participantsRequest: Set<ParticipantsRequest>,
)
