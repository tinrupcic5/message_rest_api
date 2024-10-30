package hr.vsite.messageapprestapi.errorhandling

import java.lang.Exception

class TokenValidationException(val value: String) : Exception(value)
