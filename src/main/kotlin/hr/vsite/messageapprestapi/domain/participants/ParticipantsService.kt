package hr.vsite.messageapprestapi.domain.participants

import hr.vsite.messageapprestapi.domain.chat.adapter.common.interfaces.ChatId
import hr.vsite.messageapprestapi.domain.participants.adapter.common.interfaces.ParticipantsId
import hr.vsite.messageapprestapi.domain.participants.adapter.incoming.web.ParticipantsRequest
import hr.vsite.messageapprestapi.domain.participants.adapter.outgoing.web.ParticipantsDto
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import java.util.*

@Service
@Validated
interface ParticipantsService {
    fun findAllByChatId(chatId: ChatId): Set<ParticipantsDto>
    fun delete(participantsId: ParticipantsId): String
    fun insert(participants: Set<ParticipantsRequest>)
}
