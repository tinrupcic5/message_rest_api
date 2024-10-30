package hr.vsite.messageapprestapi.domain.user.adapter.incoming.web

import hr.vsite.messageapprestapi.firebase.adapter.incoming.web.FirebaseUserRequest

data class UserRegisterRequest(
    val userName: String,
    val phoneNumber: String,
    val userEmail: String,
    val password: String,
    val firebaseUserRequest: FirebaseUserRequest,
)
