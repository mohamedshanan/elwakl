package com.shannan.nakollaol.features.generateotp

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shannan.nakollaol.core.platform.BaseViewModel
import com.shannan.nakollaol.domain.interactor.UseCase
import com.shannan.nakollaol.domain.interactor.deactivate.DeactivateUseCase
import com.shannan.nakollaol.domain.interactor.generateotp.GenerateOTPUseCase
import com.shannan.nakollaol.domain.interactor.generateotp.OTP
import javax.inject.Inject


class OTPViewModel
@Inject constructor(private val generateOTPUseCase: GenerateOTPUseCase,
                    private val deactivateUseCase: DeactivateUseCase) : BaseViewModel() {

    var otpModel: MutableLiveData<OTPModel> = MutableLiveData()
    var keyActive: MutableLiveData<Boolean> = MutableLiveData()
    private val mElapsedTime = MutableLiveData<Long>()
    private val mLifeTime = MutableLiveData<Long>()
    private val ONE_SECOND = 1000L

    fun getOTP() = generateOTPUseCase(GenerateOTPUseCase.Params(1)) { it.either(::handleFailure, ::handleSuccess) }

    fun deactivate() = deactivateUseCase(DeactivateUseCase.Params(UseCase.None())) { it.either(::handleFailure, ::handleDeactivate) }

    private fun handleSuccess(otp: OTP) {
        this.otpModel.value = OTPModel(otp.value)
        mLifeTime.postValue(otp.lifetime)
        initTimer(otp.lifetime)
    }

    private fun handleDeactivate(none: UseCase.None) {
        this.keyActive.value = true
    }

    private fun initTimer(lifetime: Long) {
        val timer = object : CountDownTimer(lifetime, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                mElapsedTime.postValue(millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                mElapsedTime.postValue(0)
            }
        }
        timer.start()
    }

    fun getElapsedTime(): LiveData<Long> {
        return mElapsedTime
    }

    fun getLifeTime(): LiveData<Long> {
        return mLifeTime
    }
}