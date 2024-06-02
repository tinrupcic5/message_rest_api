package hr.vsite.messageapprestapi.firebase

class NotificationMessage(
    val recepientToken: String,
    val title: String,
    val body: String,
    val data: Map<String, String>,
)
