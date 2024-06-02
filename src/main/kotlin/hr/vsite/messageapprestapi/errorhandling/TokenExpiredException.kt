package hr.vsite.messageapprestapi.errorhandling

import java.lang.Exception

class TokenExpiredException(val value: String) : Exception(value)
