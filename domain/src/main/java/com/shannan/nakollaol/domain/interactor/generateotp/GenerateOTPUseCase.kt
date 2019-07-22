package com.shannan.nakollaol.domain.interactor.generateotp

import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.UseCase
import com.shannan.nakollaol.domain.repository.OTPRepository
import javax.inject.Inject

class GenerateOTPUseCase
@Inject constructor(private val otpRepository: OTPRepository) : UseCase<OTP, GenerateOTPUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, OTP> = otpRepository.getOTP()

    data class Params(val timeInMillis: Long)
}
