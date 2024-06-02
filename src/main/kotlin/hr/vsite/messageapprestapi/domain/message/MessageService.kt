package hr.vsite.messageapprestapi.domain.message

import hr.vsite.messageapprestapi.domain.message.adapter.incoming.web.MessageRequest
import hr.vsite.messageapprestapi.domain.message.adapter.outgoing.web.MessageDto
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Service
@Validated
interface MessageService {
    fun findMessagesByChatId(chatId: Int): Set<MessageDto>
    fun deleteMessageById(id: Int): String
    fun sendMessage(message: MessageRequest): String
    fun readMessage(chatId: Int, userId: Int): Boolean
}
