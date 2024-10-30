package hr.vsite.messageapprestapi.domain.chat.adapter.incoming.web

import hr.vsite.messageapprestapi.domain.chat.ChatService
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.web.chat.ChatDto
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.web.chat.UserChatDto
import hr.vsite.messageapprestapi.errorhandling.DomainNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/chat")
class ChatController(
    val chatService: ChatService,
) {
    @GetMapping("/{id}")
    fun findById(@PathVariable id: String) = chatService.findById(id)

    @GetMapping("/find/{userId}")
    fun findChatByUserOrByMessageUserId(
        @PathVariable userId: Int,
    ): ResponseEntity<Set<UserChatDto>> =
        ResponseEntity.status(HttpStatus.OK).body(chatService.findChatByUserOrByMessageUserId(userId))

    @PostMapping
    fun create(@RequestBody chat: ChatParticipantsRequest): ResponseEntity<ChatDto> =
        ResponseEntity.status(HttpStatus.OK)
            .body(chatService.create(chat.chatRequest, chat.participantsRequest))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<String> {
        return try {
            ResponseEntity.status(HttpStatus.OK).body(chatService.deleteChatById(id))
        } catch (exception: DomainNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
        }
    }
}
