package com.shannan.nakollaol.data.net

import android.util.Log
import com.shannan.nakollaol.domain.exception.CodeVerificationFailure
import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.generateotp.OTP
import com.shannan.nakollaol.domain.repository.OTPRepository
import javax.inject.Inject

class TOTPRepositoryImpl
@Inject constructor() : OTPRepository {
    override fun getOTP(): Either<Failure, OTP> {
        val otpString = generate()
        return if (otpString == null) {
            Either.Left(CodeVerificationFailure.InvalidBin())
        } else {
            Either.Right(OTP(otpString, (TwoFactorAuthUtil.TIME_STEP_SECONDS * 1000).toLong()))
        }
    }

    private fun generate(): String? {
        val twoFactorAuthUtil = TwoFactorAuthUtil()

        // String base32Secret = twoFactorAuthUtil.generateBase32Secret();
        val base32Secret = "NY4A5CPJZ46LXZCP"

        // generate the QR code
        var code = twoFactorAuthUtil.generateCurrentNumber(base32Secret)
        Log.d("ToTp", code)

        return code
    }
}