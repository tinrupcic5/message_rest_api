package hr.vsite.messageapprestapi.domain.user.common.interfaces

import com.fasterxml.jackson.annotation.JsonIgnore

class User(
    val userId: UserId?,
    val userName: UserName,
    @JsonIgnore
    val password: UserPassword,
    val phoneNumber: UserPhoneNumber,
    val userEmail: UserEmail,
) {

    override fun toString(): String {
        return "User(userId=$userId, userName=$userName, " +
            "password=$password, phoneNumber=$phoneNumber, email=$userEmail)"
    }
}
