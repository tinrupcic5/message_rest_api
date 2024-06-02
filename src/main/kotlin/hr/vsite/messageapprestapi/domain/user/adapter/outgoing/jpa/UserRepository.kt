package hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa

import hr.vsite.messageapprestapi.domain.user.common.interfaces.User
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@Component
interface UserRepository {

    fun findById(id: String): Optional<User>

    fun deleteById(id: String): String

    fun existsById(id: String): Boolean
    fun insert(user: User): User
    fun findByUsernameOrEmail(usernameOrEmail: String, password: String): User?
    fun loadUserByUserEmail(value: String): User?
    fun findAll(id: String): Set<User>
    fun findChatByUserOrByMessageUserId(userId: Int): Set<User>
}
