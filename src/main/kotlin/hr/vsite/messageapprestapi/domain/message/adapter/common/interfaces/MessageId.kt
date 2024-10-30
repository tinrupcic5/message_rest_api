package hr.vsite.messageapprestapi.domain.message.adapter.common.interfaces

data class MessageId(val value: Int): Comparable<MessageId> {
    override fun compareTo(other: MessageId): Int {
        return this.value.compareTo(other.value)
    }
}
