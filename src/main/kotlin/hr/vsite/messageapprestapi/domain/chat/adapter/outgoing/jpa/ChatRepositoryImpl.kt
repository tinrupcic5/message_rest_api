package hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa

import hr.vsite.messageapprestapi.domain.chat.adapter.common.interfaces.Chat
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@Transactional(Transactional.TxType.REQUIRED)
class ChatRepositoryImpl(
    private val repository: ChatEntityJpaRepository,
) : ChatRepository {

    override fun findById(id: String): Chat =
        repository.findById(id).get().toDomain()

    @Transactional
    override fun deleteChatById(id: String): String {
        val existingChat = repository.findById(id).map { it.toDomain() }
        repository.deleteById(id)
        return existingChat.get().chatName.value
    }

    override fun existsById(id: String): Boolean =
        repository.existsById(id)

    @Transactional
    override fun create(chat: Chat) =
        repository.save(chat.toEntity())

    override fun findChatByUserOrByMessageUserId(userId: Int): Set<Chat> =
        repository.findChatByUserOrByMessageUserId(userId).setToDomain()
}
