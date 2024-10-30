package hr.vsite.messageapprestapi.domain.participants.adapter.outgoing.web

import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.web.chat.ChatDto
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.web.user.UserDto

class ParticipantsDto(
    val participantId: Int?,
    val chat: ChatDto,
    val user: UserDto,
)
