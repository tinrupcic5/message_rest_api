package hr.vsite.messageapprestapi.firebase.adapter.incoming.web

import hr.vsite.messageapprestapi.domain.user.adapter.incoming.web.UserRequest

class FirebaseUserRequest(
    val firebaseToken: String,
    val userRequest: UserRequest,
)
