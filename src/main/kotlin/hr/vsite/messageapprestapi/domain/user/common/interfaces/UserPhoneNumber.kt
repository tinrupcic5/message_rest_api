package hr.vsite.messageapprestapi.domain.user.common.interfaces

data class UserPhoneNumber(
    val value: String,
) {
    fun value(): String = value
}
