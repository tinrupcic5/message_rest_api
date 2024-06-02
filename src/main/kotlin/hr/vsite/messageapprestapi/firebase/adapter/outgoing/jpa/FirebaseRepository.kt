package hr.vsite.messageapprestapi.firebase.adapter.outgoing.jpa

import hr.vsite.messageapprestapi.firebase.common.interfaces.FirebaseUser
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
@Component
interface FirebaseRepository {

    fun findFirebaseTokenByUserId(userId: Int): FirebaseUser
    fun findFirebaseToken(token: String): String?
    fun findFirebaseTokenByChatId(chatId: Int,senderId: Int): FirebaseUser
    fun save(firebaseUser: FirebaseUser): String
}
