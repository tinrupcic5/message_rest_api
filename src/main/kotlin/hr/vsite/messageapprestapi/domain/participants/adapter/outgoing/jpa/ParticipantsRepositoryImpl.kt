package hr.vsite.messageapprestapi.domain.participants.adapter.outgoing.jpa

import hr.vsite.messageapprestapi.domain.chat.adapter.common.interfaces.ChatId
import hr.vsite.messageapprestapi.domain.participants.adapter.common.interfaces.Participants
import hr.vsite.messageapprestapi.domain.participants.adapter.common.interfaces.ParticipantsId
import hr.vsite.messageapprestapi.domain.user.common.interfaces.UserId
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.collections.Set

@Repository
@Transactional(Transactional.TxType.REQUIRED)
class ParticipantsRepositoryImpl(
    private val repository: ParticipantsEntityJpaRepository,
) : ParticipantsRepository {
    override fun findAllByChatId(chatId: ChatId): Set<Participants> =
        repository.findAllByChatId(chatId = chatId.value!!).map { it.toDomain() }.toSet()

    @Transactional
    override fun insert(participants: Set<Participants>) {
        repository.saveAll(participants.toEntity())
    }

    override fun delete(participantsId: ParticipantsId): String {
        val existingChat = repository.findById(participantsId.value.toString()).map { it.toDomain() }

        repository.deleteById(participantsId.value.toString())
        return existingChat.get().user.userName.value
    }

    override fun checkIfChatWithParticipantsExist(firstUserId: UserId, secondUserId: UserId): ChatId = ChatId(
        repository.checkIfChatWithParticipantsExist(firstUserId = firstUserId.value, secondUserId = secondUserId.value),
    )
}
