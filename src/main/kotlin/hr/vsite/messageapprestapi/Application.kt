package hr.vsite.messageapprestapi

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@EnableScheduling
class Application

@Bean
fun fireBaseMessaging(): FirebaseMessaging {
    val googleCredentials: GoogleCredentials =
        GoogleCredentials.fromStream(
            ClassPathResource("springboot-push-notifica-cf584-firebase-adminsdk-t5cz0-e81b26aebd.json").inputStream,
        )
    val options = FirebaseOptions.builder()
        .setCredentials(googleCredentials)
        .build()
    val fire = FirebaseApp.initializeApp(options, "MessageApp")
    return FirebaseMessaging.getInstance(fire)
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
