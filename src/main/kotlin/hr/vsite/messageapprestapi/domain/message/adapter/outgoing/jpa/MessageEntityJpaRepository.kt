package hr.vsite.messageapprestapi.domain.message.adapter.outgoing.jpa

import hr.vsite.messageapprestapi.domain.user.common.interfaces.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MessageEntityJpaRepository : JpaRepository<MessageEntity, String> {

    @Query(
        """
        SELECT * FROM messages WHERE chat_id = :chatId
    """,
        nativeQuery = true,
    )
    fun findAllByChatId(chatId: Int): Set<MessageEntity>

    @Query(
        """
        SELECT * FROM messages WHERE chat_id = :chatId AND user_id != :userId
    """,
        nativeQuery = true,
    )
    fun findAllByChatIdAndUserId(chatId: Int,userId: Int): Set<MessageEntity>

    @Query(
        """
         SELECT *
         FROM messages
         WHERE chat_id = :chatId
         ORDER BY created_at DESC
         LIMIT 1;
    """,
        nativeQuery = true,
    )
    fun findAllByChatIdLastMessage(chatId: Int): MessageEntity

    @Query(
        """
        DELETE FROM messages
                WHERE created_at < CURRENT_TIMESTAMP - INTERVAL '30 days';
    """,
        nativeQuery = true,
    )
    fun deleteAll30DaysOldMessages()
}
