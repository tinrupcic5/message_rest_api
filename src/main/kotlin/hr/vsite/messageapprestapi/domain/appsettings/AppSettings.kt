package hr.vsite.messageapprestapi.domain.appsettings

import hr.vsite.messageapprestapi.domain.user.common.interfaces.MessageBody

fun String.toMessageBody(): MessageBody = MessageBody(message = this)
