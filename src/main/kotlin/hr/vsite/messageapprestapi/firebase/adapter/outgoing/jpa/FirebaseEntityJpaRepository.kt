package hr.vsite.messageapprestapi.firebase.adapter.outgoing.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
internal interface FirebaseEntityJpaRepository : JpaRepository<FirebaseUserEntity, Int> {

    @Query(
        """
        SELECT * FROM firebase WHERE user_id = :userId
    """,
        nativeQuery = true,
    )
    fun findFirebaseTokenByUserId(userId: Int): FirebaseUserEntity?

    @Query(
        """
        SELECT f.*
        FROM firebase f
        JOIN participants ON f.user_id = participants.user_id
        WHERE participants.chat_id = :chatId AND participants.user_id != :senderId
        """,
        nativeQuery = true,
    )
    fun findFirebaseTokenByChatId(chatId: Int,senderId: Int): FirebaseUserEntity

    @Query(
        """
      SELECT firebase_token
      FROM firebase
      WHERE firebase_token = :token
    """,
        nativeQuery = true,
    )
    fun findFirebaseToken(token: String): String?
}
