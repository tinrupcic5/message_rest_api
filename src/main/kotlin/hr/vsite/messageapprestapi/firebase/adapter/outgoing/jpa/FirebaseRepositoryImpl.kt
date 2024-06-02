package hr.vsite.messageapprestapi.firebase.adapter.outgoing.jpa

import hr.vsite.messageapprestapi.firebase.common.interfaces.FirebaseUser
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository

@Repository
internal class FirebaseRepositoryImpl(
    private val firebaseEntityJpaRepository: FirebaseEntityJpaRepository,
) : FirebaseRepository {
    override fun findFirebaseTokenByUserId(userId: Int): FirebaseUser =
        firebaseEntityJpaRepository.findFirebaseTokenByUserId(userId)!!.entityToDomain()

    override fun findFirebaseToken(token: String): String? =
        firebaseEntityJpaRepository.findFirebaseToken(token)

    override fun findFirebaseTokenByChatId(chatId: Int,senderId: Int): FirebaseUser =
        firebaseEntityJpaRepository.findFirebaseTokenByChatId(chatId,senderId).entityToDomain()

    @Transactional
    override fun save(firebaseUser: FirebaseUser): String {
        firebaseEntityJpaRepository.save(firebaseUser.toEntity())
        return "OK"
    }
}
