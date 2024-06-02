package hr.vsite.messageapprestapi.domain.participants

import hr.vsite.messageapprestapi.domain.chat.adapter.common.interfaces.ChatId
import hr.vsite.messageapprestapi.domain.participants.adapter.common.interfaces.ParticipantsId
import hr.vsite.messageapprestapi.domain.participants.adapter.incoming.web.ParticipantsRequest
import hr.vsite.messageapprestapi.domain.participants.adapter.outgoing.jpa.ParticipantsRepository
import hr.vsite.messageapprestapi.domain.participants.adapter.outgoing.jpa.requestToDomain
import hr.vsite.messageapprestapi.domain.participants.adapter.outgoing.jpa.toDto
import hr.vsite.messageapprestapi.domain.participants.adapter.outgoing.web.ParticipantsDto
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import java.util.*

@Service
@Validated
class ParticipantsServiceImpl(
    val participantsRepository: ParticipantsRepository,
) : ParticipantsService {
    override fun findAllByChatId(chatId: ChatId): Set<ParticipantsDto> =
        participantsRepository.findAllByChatId(chatId).map { it.toDto() }.toSet()

    override fun delete(participantsId: ParticipantsId): String =
        "${participantsRepository.delete(participantsId)} has been removed from this chat"

    override fun insert(participants: Set<ParticipantsRequest>) {
        participantsRepository.insert(participants.requestToDomain())
    }
}
