package com.shannan.nakollaol.data.net

import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.generateotp.OTP
import com.shannan.nakollaol.domain.repository.OTPRepository
import java.util.*
import javax.inject.Inject

class FakeOTPRepositoryImpl
@Inject constructor() : OTPRepository {
    override fun getOTP(): Either<Failure, OTP> {
        return Either.Right(OTP((100000..1000000).random().toString(), 30000))
    }

    private fun IntRange.random() = Random().nextInt((endInclusive + 1) - start) + start
}