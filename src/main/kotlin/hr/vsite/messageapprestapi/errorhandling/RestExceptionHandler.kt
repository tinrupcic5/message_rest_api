package hr.vsite.messageapprestapi.errorhandling

import hr.vsite.messageapprestapi.domain.errorhandling.ErrorResponse
import mu.KotlinLogging
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler

@RestHandler
class RestExceptionHandler {
    private val logger = KotlinLogging.logger { }

    @ExceptionHandler
    fun handleNotAcceptableArgument(ex: MessageException): ResponseEntity<Any> {
        val errorResponse = ErrorResponse(
            HttpStatus.NOT_ACCEPTABLE.value(),
            ex.message.toString(),
            ex.localizedMessage,
        )
        logger.error(ex.localizedMessage)
        return ResponseEntity(errorResponse, HttpStatus.NOT_ACCEPTABLE)
    }

    @ExceptionHandler
    fun handleIllegalArgument(ex: DomainNotFoundException): ResponseEntity<Any> {
        val errorResponse = ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.message.toString(),
            ex.localizedMessage,
        )
        logger.error(ex.localizedMessage)
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(
        ex: DataIntegrityViolationException,
    ): ResponseEntity<Any> {
        val constraintMessages = mapOf(
            "users_phone_number_key" to "User with this phone number already exists",
            "users_email_key" to "User with this email already exists",
            "users_username_key" to "User with this username already exists",
        )
        val message = ex.localizedMessage
        val errorMessage = constraintMessages.entries.find { message.contains(it.key) }?.value
        val errorResponse = ErrorResponse(
            HttpStatus.CONFLICT.value(),
            errorMessage ?: "Data integrity violation",
            ex.localizedMessage,
        )
        logger.error(ex.localizedMessage)
        return ResponseEntity(errorResponse, HttpStatus.CONFLICT)
    }
}
