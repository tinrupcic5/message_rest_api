package hr.vsite.messageapprestapi.domain.participants.adapter.outgoing.jpa

import hr.vsite.messageapprestapi.domain.chat.adapter.common.interfaces.ChatId
import hr.vsite.messageapprestapi.domain.participants.adapter.common.interfaces.Participants
import hr.vsite.messageapprestapi.domain.participants.adapter.common.interfaces.ParticipantsId
import hr.vsite.messageapprestapi.domain.user.common.interfaces.UserId
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@Component
interface ParticipantsRepository {
    fun findAllByChatId(chatId: ChatId): Set<Participants>
    fun insert(participants: Set<Participants>)
    fun delete(participantsId: ParticipantsId): String
    fun checkIfChatWithParticipantsExist(firstUserId: UserId, secondUserId: UserId): ChatId
}
