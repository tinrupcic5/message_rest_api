package hr.vsite.messageapprestapi.domain.message.adapter.incoming.job

import hr.vsite.messageapprestapi.domain.message.adapter.outgoing.jpa.MessageRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class MessageRepositoryScheduler(
    private val messageRepository: MessageRepository,
) {

    @Scheduled(cron = "\${msg.jobs.message-schedule}")
    fun callRepository() {
        messageRepository.deleteAll30DaysOldMessages()
    }
}
