package hr.vsite.messageapprestapi.domain.user.common.interfaces

data class UserId(
    val value: Int,
) {
    fun value(): Int = value
}
