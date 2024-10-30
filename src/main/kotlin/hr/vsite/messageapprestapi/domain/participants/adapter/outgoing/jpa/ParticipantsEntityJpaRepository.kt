package hr.vsite.messageapprestapi.domain.participants.adapter.outgoing.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ParticipantsEntityJpaRepository : JpaRepository<ParticipantsEntity, String> {
    override fun findById(id: String): Optional<ParticipantsEntity>

    @Query(
        """
        SELECT * FROM participants WHERE chat_id = :chatId
    """,
        nativeQuery = true,
    )
    fun findAllByChatId(chatId: Int): Set<ParticipantsEntity>

    @Query(
        """
         SELECT chat_id
         FROM participants
         WHERE user_id = :firstUserId
         AND chat_id IN (
            SELECT chat_id
            FROM participants
            WHERE user_id = :secondUserId
            );
    """,
        nativeQuery = true,
    )
    fun checkIfChatWithParticipantsExist(firstUserId: Int, secondUserId: Int): Int?
}
