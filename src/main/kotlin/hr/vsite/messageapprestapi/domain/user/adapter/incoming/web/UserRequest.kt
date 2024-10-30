package hr.vsite.messageapprestapi.domain.user.adapter.incoming.web

data class UserRequest(
    val userId: Int?,
    val userName: String,
    val phoneNumber: String,
    val userEmail: String,
)
