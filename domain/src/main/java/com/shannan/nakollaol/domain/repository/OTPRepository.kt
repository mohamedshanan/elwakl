package com.shannan.nakollaol.domain.repository

import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.generateotp.OTP

interface OTPRepository {
    fun getOTP(): Either<Failure, OTP>
}
