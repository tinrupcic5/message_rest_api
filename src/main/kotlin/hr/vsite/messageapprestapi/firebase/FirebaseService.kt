package hr.vsite.messageapprestapi.firebase

import hr.vsite.messageapprestapi.firebase.adapter.incoming.web.FirebaseUserRequest
import hr.vsite.messageapprestapi.firebase.adapter.outgoing.web.FirebaseUserDto
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Service
@Validated
interface FirebaseService {

    fun findFirebaseTokenByUserId(userId: Int): FirebaseUserDto?

    fun findFirebaseTokenByChatId(chatId: Int, senderId: Int):FirebaseUserDto

    fun save(firebaseUserRequest: FirebaseUserRequest): String
}
