package hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa

import hr.vsite.messageapprestapi.domain.user.common.interfaces.User
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@Transactional(Transactional.TxType.REQUIRED)
internal class UserRepositoryImpl(
    private val userEntityJpaRepository: UserEntityJpaRepository,
) : UserRepository {

    override fun findChatByUserOrByMessageUserId(userId: Int): Set<User> =
        userEntityJpaRepository.findChatByUserOrByMessageUserId(userId).setToDomain()

    @Transactional
    override fun insert(user: User): User {
        userEntityJpaRepository.save(
            UserEntity(
                user.userId?.value,
                user.userName.value,
                user.password.value,
                user.userEmail.value,
                user.phoneNumber.value,
            ),
        )
        return user
    }

    override fun findById(id: String): Optional<User> =
        userEntityJpaRepository.findById(id).map { it.toDomain() }

    @Transactional
    override fun deleteById(id: String): String {
        val existingUser = userEntityJpaRepository.findById(id).map { it.toDomain() }
        userEntityJpaRepository.deleteById(id)
        return existingUser.get().userName.value
    }

    override fun existsById(id: String): Boolean =
        userEntityJpaRepository.existsById(id)



    override fun findByUsernameOrEmail(usernameOrEmail: String, password: String): User? =
        userEntityJpaRepository.findByUsernameOrEmailAndPassword(usernameOrEmail, usernameOrEmail, password)?.toDomain()

    override fun loadUserByUserEmail(value: String): User? {
        return userEntityJpaRepository.loadUserByUserEmail(value)?.toDomain()
    }

    override fun findAll(id: String): Set<User> =
        userEntityJpaRepository.findAll(id.toInt()).map { it.toDomain() }.toSet()


}
