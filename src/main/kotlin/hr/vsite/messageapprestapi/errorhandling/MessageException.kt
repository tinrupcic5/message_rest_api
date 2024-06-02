package hr.vsite.messageapprestapi.errorhandling

import java.lang.Exception

class MessageException(val value: String) : Exception(value)
