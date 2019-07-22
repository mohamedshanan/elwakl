package com.shannan.nakollaol.domain.interactor.login

import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.UseCase
import com.shannan.nakollaol.domain.interactor.User
import com.shannan.nakollaol.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase
@Inject constructor(private val userRepository: UserRepository) : UseCase<User, LoginUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, User> = userRepository.login(params.user)

    data class Params(val user: User)
}
