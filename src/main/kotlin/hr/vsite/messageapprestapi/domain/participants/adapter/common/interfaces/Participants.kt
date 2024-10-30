package hr.vsite.messageapprestapi.domain.participants.adapter.common.interfaces

import hr.vsite.messageapprestapi.domain.chat.adapter.common.interfaces.Chat
import hr.vsite.messageapprestapi.domain.user.common.interfaces.User

data class Participants(
    val participantId: ParticipantsId?,
    val chat: Chat,
    val user: User,
) {
    override fun toString(): String {
        return "Participants(participantId=$participantId, chat=$chat, user=$user)"
    }
}
