package hr.vsite.messageapprestapi.domain.participants.adapter.outgoing.jpa

import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa.ChatEntity
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa.requestToDomain
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa.toDomain
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa.toDto
import hr.vsite.messageapprestapi.domain.chat.adapter.outgoing.jpa.toEntity
import hr.vsite.messageapprestapi.domain.participants.adapter.common.interfaces.Participants
import hr.vsite.messageapprestapi.domain.participants.adapter.common.interfaces.ParticipantsId
import hr.vsite.messageapprestapi.domain.participants.adapter.incoming.web.ParticipantsRequest
import hr.vsite.messageapprestapi.domain.participants.adapter.outgoing.web.ParticipantsDto
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.UserEntity
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.requestToDomain
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.toDomain
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.toDto
import hr.vsite.messageapprestapi.domain.user.adapter.outgoing.jpa.toEntity
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
@Table(name = "participants")
data class ParticipantsEntity(
    @Id
    @Column(name = "participant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val participantId: Int?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    val chat: ChatEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserEntity,
)

fun ParticipantsEntity.toDomain(): Participants =
    Participants(
        participantId = ParticipantsId(participantId!!),
        chat = chat.toDomain(),
        user = user.toDomain(),
    )

fun Participants.toDto(): ParticipantsDto =
    ParticipantsDto(
        participantId = this.participantId!!.value,
        chat = this.chat.toDto(),
        user = this.user.toDto(),
    )

fun Set<Participants>.toEntity(): Set<ParticipantsEntity> {
    return map {
        ParticipantsEntity(
            participantId = it.participantId?.value,
            chat = it.chat.toEntity(),
            user = it.user.toEntity(),
        )
    }.toSet()
}

fun Set<ParticipantsDto>.toDomain(): Set<Participants> {
    return map {
        Participants(
            participantId = ParticipantsId(it.participantId!!),
            chat = it.chat.toDomain(),
            user = it.user.toDomain(),
        )
    }.toSet()
}

fun Set<ParticipantsRequest>.requestToDomain(): Set<Participants> {
    return map {
        Participants(
            participantId = null,
            chat = it.chat.requestToDomain(),
            user = it.user.requestToDomain(),
        )
    }.toSet()
}
