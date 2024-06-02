package hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.web.chat

import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.web.user.UserDto

data class UserChatDto(
    val chatDto: ChatDto,
    val userDto: UserDto,
)
