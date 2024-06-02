package hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa

import hr.vsite.messageapprestapi.domain.user.adapter.incoming.web.UserRegisterRequest
import hr.vsite.messageapprestapi.domain.user.adapter.incoming.web.UserRequest
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.web.user.UserDto
import hr.vsite.messageapprestapi.domain.user.common.interfaces.User
import hr.vsite.messageapprestapi.domain.user.common.interfaces.UserEmail
import hr.vsite.messageapprestapi.domain.user.common.interfaces.UserId
import hr.vsite.messageapprestapi.domain.user.common.interfaces.UserName
import hr.vsite.messageapprestapi.domain.user.common.interfaces.UserPassword
import hr.vsite.messageapprestapi.domain.user.common.interfaces.UserPhoneNumber
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId: Int?,

    @Column(name = "username")
    val username: String,

    @Column(name = "password")
    var password: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "phone_number")
    val phoneNumber: String,
)
fun Set<UserEntity>.setToDomain(): Set<User> {
    return this.map { userEntity ->
        User(
            userId = userEntity.userId?.let { UserId(it) },
            userName = UserName(userEntity.username),
            password = UserPassword(userEntity.password),
            phoneNumber = UserPhoneNumber(userEntity.phoneNumber),
            userEmail = UserEmail(userEntity.email),
        )
    }.toSet()
}

fun Set<User>.setToDto(): Set<UserDto> {
    return this.map { user ->
        UserDto(
            userId = user.userId?.value,
            userName = user.userName.value,
            phoneNumber = user.phoneNumber.value,
            userEmail = user.userEmail.value,
        )
    }.toSet()
}

fun UserEntity.toDomain(): User =
    User(
        userId = UserId(this.userId!!),
        userName = UserName(this.username),
        password = UserPassword(this.password),
        phoneNumber = UserPhoneNumber(this.phoneNumber),
        userEmail = UserEmail(this.email),
    )

fun User.toEntity(): UserEntity =
    UserEntity(
        userId = this.userId?.value,
        username = this.userName.value,
        password = this.password.value!!,
        email = this.userEmail.value,
        phoneNumber = this.phoneNumber.value,
    )

fun UserEntity.entityToDto() =
    UserDto(
        userId = this.userId,
        userName = this.username,
        phoneNumber = this.phoneNumber,
        userEmail = this.email,
    )

fun User.toDto() = UserDto(
    userId = this.userId?.value,
    userName = this.userName.value,
    phoneNumber = this.phoneNumber.value,
    userEmail = this.userEmail.value,
)

fun UserDto.toDomain() = User(
    userId = UserId(this.userId!!),
    userName = UserName(this.userName),
    phoneNumber = UserPhoneNumber(this.phoneNumber),
    userEmail = UserEmail(this.userEmail),
    password = UserPassword(""),
)

fun UserRegisterRequest.toDomain() = User(
    userId = null,
    userName = UserName(this.userName),
    phoneNumber = UserPhoneNumber(this.phoneNumber),
    userEmail = UserEmail(this.userEmail),
    password = UserPassword(this.password),
)

fun UserRequest.requestToDomain() = User(
    userId = UserId(this.userId!!),
    userName = UserName(this.userName),
    phoneNumber = UserPhoneNumber(this.phoneNumber),
    userEmail = UserEmail(this.userEmail),
    password = UserPassword(""),
)
