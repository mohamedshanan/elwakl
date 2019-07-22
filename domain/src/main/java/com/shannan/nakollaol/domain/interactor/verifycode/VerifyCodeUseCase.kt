package com.shannan.nakollaol.domain.interactor.verifycode

import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.User
import com.shannan.nakollaol.domain.interactor.UseCase
import com.shannan.nakollaol.domain.repository.KeyRepository
import javax.inject.Inject

class VerifyCodeUseCase
@Inject constructor(private val keyRepository: KeyRepository) : UseCase<User, VerifyCodeUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, User> = keyRepository.verifyCode(params.verificationCode)

    data class Params(val verificationCode: String)
}
