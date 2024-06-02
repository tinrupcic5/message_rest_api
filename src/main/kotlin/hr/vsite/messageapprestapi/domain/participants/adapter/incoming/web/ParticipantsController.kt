package hr.vsite.messageapprestapi.domain.participants.adapter.incoming.web

import hr.vsite.messageapprestapi.domain.chat.adapter.common.interfaces.ChatId
import hr.vsite.messageapprestapi.domain.participants.ParticipantsService
import hr.vsite.messageapprestapi.domain.participants.adapter.common.interfaces.ParticipantsId
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/participants")
class ParticipantsController(
    val participantsService: ParticipantsService,
) {
    @GetMapping("/{chatId}")
    fun findAllByChatId(@PathVariable chatId: Int) =
        participantsService.findAllByChatId(ChatId(chatId))

    @PostMapping
    fun insert(@RequestBody participants: Set<ParticipantsRequest>) =
        participantsService.insert(participants)

    @DeleteMapping("/{participantId}")
    fun delete(@PathVariable participantId: Int) =
        participantsService.delete(ParticipantsId(participantId))
}
