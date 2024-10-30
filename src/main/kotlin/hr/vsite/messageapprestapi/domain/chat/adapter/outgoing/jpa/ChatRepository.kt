package hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa

import hr.vsite.messageapprestapi.domain.chat.adapter.common.interfaces.Chat
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@Component
interface ChatRepository {

    fun findById(id: String): Chat
    fun deleteChatById(id: String): String
    fun existsById(id: String): Boolean
    fun create(chat: Chat): ChatEntity
    fun findChatByUserOrByMessageUserId(userId: Int): Set<Chat>
}
