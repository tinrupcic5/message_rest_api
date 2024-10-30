package hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ChatEntityJpaRepository : JpaRepository<ChatEntity, String> {
    override fun findById(id: String): Optional<ChatEntity>

    @Query(
        """
SELECT c.*
FROM users u
         JOIN participants p ON u.user_id = p.user_id
         JOIN chats c ON p.chat_id = c.chat_id
WHERE u.user_id = :userId
        """,
        nativeQuery = true,
    )
    fun findChatByUserOrByMessageUserId(userId: Int): Set<ChatEntity>
}
