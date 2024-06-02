package hr.vsite.messageapprestapi.domain.errorhandling

data class ErrorResponse(
    val errorCode: Int,
    val errorMessage: String,
    val exceptionMessage: String,
)
