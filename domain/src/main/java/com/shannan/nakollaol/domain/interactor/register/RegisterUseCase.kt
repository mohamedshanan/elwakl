package com.shannan.nakollaol.domain.interactor.register

import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.UseCase
import com.shannan.nakollaol.domain.interactor.User
import com.shannan.nakollaol.domain.repository.UserRepository
import javax.inject.Inject

class RegisterUseCase
@Inject constructor(private val userRepository: UserRepository) : UseCase<User, RegisterUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, User> = userRepository.register(params.user)

    data class Params(val user: User)
}
