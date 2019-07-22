
package com.shannan.nakollaol.features.codeverification

import androidx.lifecycle.MutableLiveData
import com.shannan.nakollaol.core.platform.BaseViewModel
import com.shannan.nakollaol.domain.interactor.User
import com.shannan.nakollaol.domain.interactor.cacheuser.CacheUserUseCase
import com.shannan.nakollaol.domain.interactor.verifycode.VerifyCodeUseCase
import com.shannan.nakollaol.domain.interactor.verifycode.VerifyCodeUseCase.Params
import javax.inject.Inject

class VerificationViewModel
@Inject constructor(private val verifyCode: VerifyCodeUseCase, private val cacheUserUseCase: CacheUserUseCase) : BaseViewModel() {

    var userModel: MutableLiveData<UserModel> = MutableLiveData()

    fun verifyCode(verificationCode: String) = verifyCode(Params(verificationCode)) { it.either(::handleFailure, ::handleKey) }

    private fun handleKey(user: User) {
        cacheUserUseCase(CacheUserUseCase.Params(user)) { it.either(::handleFailure, ::keySaved) }
    }

    private fun keySaved(user: User) {
        this.userModel.value = UserModel(user.name, user.email, user.phone, user.token)
    }
}