package hr.vsite.messageapprestapi.domain.user.common.interfaces

data class UserEmail(
    val value: String,
) {
    fun value(): String = value
}
