package hr.vsite.messageapprestapi.properties

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AppProperties {
    @Value("\${firebase.recepientToken}")
    lateinit var recepientToken: String
}
