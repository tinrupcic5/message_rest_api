package hr.vsite.messageapprestapi.domain.user

import hr.vsite.messageapprestapi.domain.user.adapter.incoming.web.UserRegisterRequest
import hr.vsite.messageapprestapi.domain.user.adapter.incoming.web.UserRequest
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.UserRepository
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.toDomain
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.toDto
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.web.user.UserDto
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.web.user.toDto
import hr.vsite.messageapprestapi.errorhandling.DomainNotFoundException
import hr.vsite.messageapprestapi.firebase.adapter.incoming.web.FirebaseUserRequest
import hr.vsite.messageapprestapi.firebase.adapter.outgoing.jpa.FirebaseRepository
import hr.vsite.messageapprestapi.firebase.adapter.outgoing.jpa.requestToDomain
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Service
@Validated
class UserServiceImpl(
    val userRepository: UserRepository,
    val firebaseRepository: FirebaseRepository,
) : UserService {

    override fun findByUsernameOrEmailWithPass(usernameOrEmail: String, password: String, firebaseToken: String): UserDto? {
        val fire = firebaseRepository.findFirebaseToken(firebaseToken)
        if (fire == null) {
            val user = userRepository.findByUsernameOrEmail(usernameOrEmail, password)
            val firebaseRequest = FirebaseUserRequest(
                firebaseToken,
                UserRequest(
                    userId = user?.userId?.value,
                    userName = user?.userName!!.value,
                    phoneNumber = user.phoneNumber.value,
                    userEmail = user.userEmail.value,
                ),
            )
            firebaseRepository.save(firebaseRequest.requestToDomain())
        }

        return userRepository.findByUsernameOrEmail(usernameOrEmail, password)?.toDto()
            ?: throw DomainNotFoundException("User $usernameOrEmail not found.")
    }

    override fun findById(id: String): UserDto? =
        userRepository.findById(id).toDto()

    override fun deleteById(id: String): String {
        val exists = userRepository.existsById(id)
        if (!exists) {
            throw DomainNotFoundException("User with id: $id does not exists!")
        }
        return "User : ${userRepository.deleteById(id)} has been deleted!"
    }

    override fun insert(user: UserRegisterRequest): String {
        val usr = userRepository.insert(user.toDomain())

        val log = userRepository.findByUsernameOrEmail(usr.userEmail.value, usr.password.value)
        val firebaseRequest = FirebaseUserRequest(
            user.firebaseUserRequest.firebaseToken,
            UserRequest(
                userId = log?.userId?.value,
                userName = log?.userName!!.value,
                phoneNumber = log.phoneNumber.value,
                userEmail = log.userEmail.value,
            ),
        )

        firebaseRepository.save(firebaseRequest.requestToDomain())
        return "User : ${usr.userName.value} is inserted!"
    }



    override fun findAll(id: String): Set<UserDto> = userRepository.findAll(id).map { it.toDto() }.toSet()
}
