package hr.vsite.messageapprestapi.domain.chat

import hr.vsite.messageapprestapi.domain.chat.adapter.incoming.web.ChatRequest
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.web.chat.ChatDto
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.web.chat.UserChatDto
import hr.vsite.messageapprestapi.domain.participants.adapter.incoming.web.ParticipantsRequest
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Service
@Validated
interface ChatService {
    fun findById(id: String): ChatDto
    fun findChatByUserOrByMessageUserId(userId: Int): Set<UserChatDto>
    fun deleteChatById(id: String): String
    fun create(chat: ChatRequest, participants: Set<ParticipantsRequest>): ChatDto
}
