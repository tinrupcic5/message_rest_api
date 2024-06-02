package hr.vsite.messageapprestapi.domain.message.adapter.outgoing.jpa

import hr.vsite.messageapprestapi.domain.message.adapter.common.interfaces.Message
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
@Component
interface MessageRepository {

    fun findMessagesByChatId(chatId: Int): Set<Message>
    fun findAllByChatIdLastMessage(chatId: Int): Message
    fun deleteMessageById(id: Int)
    fun deleteAll30DaysOldMessages()
    fun existsById(id: Int): Boolean
    fun sendMessage(message: Message)
    fun readMessage(chatId: Int, userId: Int): Boolean
}
