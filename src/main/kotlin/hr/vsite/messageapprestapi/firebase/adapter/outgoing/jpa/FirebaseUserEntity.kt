package hr.vsite.messageapprestapi.firebase.adapter.outgoing.jpa

import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa.toEntity
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.UserEntity
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.requestToDomain
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.toDomain
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.toEntity
import hr.vsite.messageapprestapi.firebase.adapter.incoming.web.FirebaseUserRequest
import hr.vsite.messageapprestapi.firebase.adapter.outgoing.web.FirebaseUserDto
import hr.vsite.messageapprestapi.firebase.common.interfaces.FirebaseUser
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "firebase")
data class FirebaseUserEntity(
    @Id
    @Column(name = "firebase_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val firebaseId: Int?,

    @Column(name = "firebase_token")
    val firebaseToken: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserEntity,
)

fun Set<FirebaseUserEntity>.setEntityToDomain(): Set<FirebaseUser> {
    return map {
        FirebaseUser(
            firebaseId = it.firebaseId,
            firebaseToken = it.firebaseToken,
            user = it.user.toDomain(),
        )
    }.toSet()
}

fun Set<FirebaseUser>.setDomainToDto(): Set<FirebaseUserDto> {
    return map {
        FirebaseUserDto(
            firebaseToken = it.firebaseToken,
        )
    }.toSet()
}

fun FirebaseUserEntity.toDto() =
    FirebaseUserDto(
        firebaseToken = this.firebaseToken,
    )

fun FirebaseUserEntity.entityToDomain() =
    FirebaseUser(
        firebaseId = this.firebaseId,
        firebaseToken = this.firebaseToken,
        user = this.user.toDomain(),
    )

fun FirebaseUserRequest.requestToDomain() =
    FirebaseUser(
        firebaseId = null,
        firebaseToken = this.firebaseToken,
        user = userRequest.requestToDomain(),
    )

fun FirebaseUser.toEntity() =
    FirebaseUserEntity(
        firebaseId = this.firebaseId,
        firebaseToken = this.firebaseToken,
        user = this.user.toEntity(),
    )

fun FirebaseUser.domainToDto() =
    FirebaseUserDto(
        firebaseToken = this.firebaseToken,
    )
