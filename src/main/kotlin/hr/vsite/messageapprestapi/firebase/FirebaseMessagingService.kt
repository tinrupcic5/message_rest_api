package hr.vsite.messageapprestapi.firebase

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class FirebaseMessagingService(
    var firebaseMessaging: FirebaseMessaging,
) {
    private val logger = KotlinLogging.logger { }

    fun sendNotificationByToken(msg: NotificationMessage): String {
        val notification = Notification
            .builder()
            .setTitle(msg.title)
            .setBody(msg.body)
            .build()
        val mesage = Message
            .builder()
            .setToken(msg.recepientToken)
            .setNotification(notification)
            .putAllData(msg.data)
            .build()
        try {
            firebaseMessaging.send(mesage)
            System.out.println("")
            logger.info("SEND : " + msg.recepientToken)

            return "Success"
        } catch (e: Exception) {
            e.printStackTrace()
            System.out.println("")
            logger.error("Error  ")
            return "Error"
        }
    }
}
