package hr.vsite.messageapprestapi.domain.user.ports.incomming.usecase

interface UserIncommingFacade {

    sealed interface UserIncommingResult {
        object Success : UserIncommingResult

        object AlreadyExists : UserIncommingResult

        object NotFound : UserIncommingResult
    }
}
