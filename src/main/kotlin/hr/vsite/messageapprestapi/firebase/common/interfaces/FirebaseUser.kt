package hr.vsite.messageapprestapi.firebase.common.interfaces

import hr.vsite.messageapprestapi.domain.user.common.interfaces.User

class FirebaseUser(
    val firebaseId: Int?,
    val firebaseToken: String,
    val user: User,
)
