package hr.vsite.messageapprestapi.domain.user

import hr.vsite.messageapprestapi.domain.user.adapter.incoming.web.UserRegisterRequest
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.web.user.UserDto
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Service
@Validated
interface UserService {

    fun findByUsernameOrEmailWithPass(usernameOrEmail: String, password: String, firebaseToken: String): UserDto?

    fun insert(user: UserRegisterRequest): String

    fun findById(id: String): UserDto?

    fun deleteById(id: String): String

    fun findAll(id: String): Set<UserDto>
}
