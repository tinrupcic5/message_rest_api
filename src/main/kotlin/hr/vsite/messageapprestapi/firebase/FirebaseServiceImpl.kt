package hr.vsite.messageapprestapi.firebase

import hr.vsite.messageapprestapi.firebase.adapter.incoming.web.FirebaseUserRequest
import hr.vsite.messageapprestapi.firebase.adapter.outgoing.jpa.FirebaseRepository
import hr.vsite.messageapprestapi.firebase.adapter.outgoing.jpa.domainToDto
import hr.vsite.messageapprestapi.firebase.adapter.outgoing.jpa.requestToDomain
import hr.vsite.messageapprestapi.firebase.adapter.outgoing.jpa.setDomainToDto
import hr.vsite.messageapprestapi.firebase.adapter.outgoing.web.FirebaseUserDto
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Service
@Validated
class FirebaseServiceImpl(
    val firebaseRepository: FirebaseRepository,
) : FirebaseService {
    override fun findFirebaseTokenByUserId(userId: Int): FirebaseUserDto? =
        firebaseRepository.findFirebaseTokenByUserId(userId).domainToDto()

    override fun findFirebaseTokenByChatId(chatId: Int,senderId: Int): FirebaseUserDto =
        firebaseRepository.findFirebaseTokenByChatId(chatId,senderId).domainToDto()

    override fun save(firebaseUserRequest: FirebaseUserRequest): String =
        firebaseRepository.save(firebaseUserRequest.requestToDomain())
}
