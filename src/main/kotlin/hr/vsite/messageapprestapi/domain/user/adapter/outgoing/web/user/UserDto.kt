package hr.vsite.messageapprestapi.domain.user.adapter.outgoing.web.user

import hr.vsite.messageapprestapi.domain.user.common.interfaces.User
import java.util.*

data class UserDto(
    val userId: Int?,
    val userName: String,
    val phoneNumber: String,
    val userEmail: String,
    val bearerToken: String? = null
)

fun Optional<User>.toDto() = UserDto(
    userId = this.get().userId?.value,
    userName = this.get().userName.value,
    phoneNumber = this.get().phoneNumber.value,
    userEmail = this.get().userEmail.value,
)
