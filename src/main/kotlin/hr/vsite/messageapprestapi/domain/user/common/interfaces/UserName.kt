package hr.vsite.messageapprestapi.domain.user.common.interfaces

data class UserName(
    val value: String,
) {
    fun value(): String = value
}
