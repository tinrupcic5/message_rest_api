package hr.vsite.messageapprestapi.errorhandling

import java.lang.Exception

class DomainNotFoundException(val value: String) : Exception(value)
