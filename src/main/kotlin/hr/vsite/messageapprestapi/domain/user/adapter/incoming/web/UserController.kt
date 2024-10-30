package hr.vsite.messageapprestapi.domain.user.adapter.incoming.web

import hr.vsite.messageapprestapi.domain.user.UserService
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.web.user.UserDto
import hr.vsite.messageapprestapi.domain.user.common.interfaces.MessageBody
import hr.vsite.messageapprestapi.security.jwt.JwtService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(
    val userService: UserService,
    val jwtService: JwtService,
) {
    @GetMapping("/login")
    fun login(
        @RequestParam username: String,
        @RequestParam password: String,
        @RequestParam firebaseToken: String,
    ): ResponseEntity<UserDto> {

        val usr = userService.findByUsernameOrEmailWithPass(username, password, firebaseToken)
        return ResponseEntity.status(HttpStatus.OK).body(
            usr?.copy(bearerToken = jwtService.create(userId = usr.userId!!.toLong()))
        )

    }


    @PostMapping("/register")
    fun register(@RequestBody user: UserRegisterRequest): ResponseEntity<MessageBody> =
        ResponseEntity.status(HttpStatus.OK).body(MessageBody(userService.insert(user)))


    @GetMapping("/{id}")
    fun findAll(@PathVariable id: String): Set<UserDto> = userService.findAll(id)

    @GetMapping("/find/{id}")
    fun find(@PathVariable id: String): UserDto? =
        userService.findById(id)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.OK).body(userService.deleteById(id))


}
