package hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
internal interface UserEntityJpaRepository : JpaRepository<UserEntity, String> {

    @Query(
        """
SELECT users.*
FROM users
         JOIN participants ON participants.user_id = users.user_id
         JOIN chats ON chats.chat_id = participants.chat_id
WHERE chats.chat_id IN (
    SELECT chat_id
    FROM participants
             JOIN users ON participants.user_id = users.user_id
    WHERE users.user_id = :userId
) AND users.user_id != :userId

        """,
        nativeQuery = true,
    )
    fun findChatByUserOrByMessageUserId(userId: Int): Set<UserEntity>

    override fun findById(id: String): Optional<UserEntity>

    @Query(
        """
        SELECT * FROM users WHERE (username =:username OR email =:email) AND password =:password
    """,
        nativeQuery = true,
    )
    fun findByUsernameOrEmailAndPassword(username: String, email: String, password: String): UserEntity?

    @Query(
        """
        SELECT * FROM users WHERE username =:email OR email =:email
    """,
        nativeQuery = true,
    )
    fun loadUserByUserEmail(email: String): UserEntity?

    @Query(
        """
        SELECT * FROM users WHERE user_id != :id
    """,
        nativeQuery = true,
    )
    fun findAll(id: Int): Set<UserEntity>


}
