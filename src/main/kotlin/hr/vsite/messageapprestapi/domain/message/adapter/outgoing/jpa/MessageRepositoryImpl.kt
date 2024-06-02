package hr.vsite.messageapprestapi.domain.message.adapter.outgoing.jpa

import hr.vsite.messageapprestapi.domain.message.adapter.common.interfaces.Message
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@Transactional(Transactional.TxType.REQUIRED)
class MessageRepositoryImpl(
    private val messageEntityJpaRepository: MessageEntityJpaRepository,
) : MessageRepository {
    override fun findMessagesByChatId(chatId: Int): Set<Message> =
        messageEntityJpaRepository.findAllByChatId(chatId).map { it.toDomain() }.toSet()

    override fun findAllByChatIdLastMessage(chatId: Int): Message =
        messageEntityJpaRepository.findAllByChatIdLastMessage(chatId).toDomain()

    @Transactional
    override fun deleteMessageById(id: Int) {
        messageEntityJpaRepository.deleteById(id.toString())
    }

    override fun deleteAll30DaysOldMessages() {
        messageEntityJpaRepository.deleteAll30DaysOldMessages()
    }

    override fun existsById(id: Int): Boolean =
        messageEntityJpaRepository.existsById(id.toString())

    @Transactional
    override fun sendMessage(message: Message) {
        messageEntityJpaRepository.save(message.toEntity())
    }

    @Modifying
    override fun readMessage(chatId: Int, userId: Int): Boolean {
        val messages = messageEntityJpaRepository.findAllByChatIdAndUserId(chatId, userId)
        val updatedMessages = messages.map {
            it.copy(isRead = true)
        }
        val savedMessages = messageEntityJpaRepository.saveAll(updatedMessages)
        return savedMessages.isNotEmpty()
    }
}
