package hr.vsite.messageapprestapi.domain.message.adapter.incoming.web

import hr.vsite.messageapprestapi.domain.message.MessageService
import hr.vsite.messageapprestapi.domain.user.common.interfaces.MessageBody
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/message")
class MessageController(
    val messageService: MessageService,
) {
    @GetMapping("/{chatId}")
    fun findMessagesByChatId(@PathVariable chatId: Int) = messageService.findMessagesByChatId(chatId)

    @PostMapping
    fun sendMessage(@RequestBody message: MessageRequest): ResponseEntity<MessageBody> =
        ResponseEntity.status(HttpStatus.OK).body(MessageBody(messageService.sendMessage(message)))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<MessageBody> =
        ResponseEntity.status(HttpStatus.OK).body(MessageBody(messageService.deleteMessageById(id)))

    @PutMapping("/read/{chatId}/{userId}")
    fun readMessage(@PathVariable chatId: Int,@PathVariable userId: Int): ResponseEntity<Boolean> =
        ResponseEntity.status(HttpStatus.OK).body(messageService.readMessage(chatId,userId))
}
