package com.shannan.nakollaol.features.splash

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.shannan.nakollaol.core.platform.BaseViewModel
import com.shannan.nakollaol.domain.interactor.User
import com.shannan.nakollaol.domain.interactor.UseCase
import com.shannan.nakollaol.domain.interactor.checkCachedKey.GetCachedUserUseCase
import javax.inject.Inject

class SplashViewModel
@Inject constructor(private val getCachedUserUseCase: GetCachedUserUseCase) : BaseViewModel() {

    var progress = MutableLiveData<Int>()
    var cachedUser: MutableLiveData<User> = MutableLiveData()

    fun isKeyCached() = run {
        progress.value = View.VISIBLE
        getCachedUserUseCase(GetCachedUserUseCase.Params(UseCase.None()))
        { it.either(::handleFailure, ::setUserLiveDataValue) }
    }

    private fun setUserLiveDataValue(user: User) {
        progress.value = View.INVISIBLE
        this.cachedUser.value = user
    }
}