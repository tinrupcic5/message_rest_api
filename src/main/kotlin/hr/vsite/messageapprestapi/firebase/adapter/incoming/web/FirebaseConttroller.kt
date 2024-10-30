package hr.vsite.messageapprestapi.firebase.adapter.incoming.web

import hr.vsite.messageapprestapi.firebase.FirebaseMessagingService
import hr.vsite.messageapprestapi.firebase.NotificationMessage
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notification")
class FirebaseConttroller(
    val firebaseMessagingService: FirebaseMessagingService,
) {

    @PostMapping
    fun sendNotificationByToken(@RequestBody notificationMessage: NotificationMessage): String {
        return firebaseMessagingService.sendNotificationByToken(notificationMessage)
    }
}
