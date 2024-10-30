package hr.vsite.messageapprestapi.errorhandling

import java.lang.Exception

class UserDisabledException(val value: String) : Exception(value)
