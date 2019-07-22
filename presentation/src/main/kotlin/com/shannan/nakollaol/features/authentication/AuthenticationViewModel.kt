package com.shannan.nakollaol.features.authentication

import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.shannan.nakollaol.core.platform.BaseViewModel
import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.interactor.User
import com.shannan.nakollaol.domain.interactor.cacheuser.CacheUserUseCase
import com.shannan.nakollaol.domain.interactor.login.LoginUseCase
import com.shannan.nakollaol.domain.interactor.login.LoginUseCase.Params
import javax.inject.Inject

class AuthenticationViewModel
@Inject constructor(private val login: LoginUseCase, private val cacheUserUseCase: CacheUserUseCase) : BaseViewModel() {

    var userModel: MutableLiveData<UserModel> = MutableLiveData()
    var validateError: MutableLiveData<Failure> = MutableLiveData()

    fun authenticate(input_email: EditText, input_name: EditText, input_phone: EditText) {
        val tempUser = validateInput(input_email, input_name, input_phone)
        if (tempUser != null) {
            login(Params(tempUser)) { it.either(::handleFailure, ::handleUserAuthentication) }
        }
    }

    private fun validateInput(input_email: EditText, input_name: EditText, input_phone: EditText): User? {

        var featureFailure: Failure.FeatureFailure? = null

        val email = input_email.text
        if (email.isNullOrEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            featureFailure = AuthFailure.InvalidEmail()
        } else {
            input_email.error = null
        }

        val name = input_name.text
        if (name.isNullOrEmpty()) {
            featureFailure = AuthFailure.InvalidName()
        } else {
            input_name.error = null
        }

        val phone = input_phone.text
        if (phone.isNullOrEmpty()) {
            featureFailure = AuthFailure.InvalidPhone()
        } else {
            input_phone.error = null
        }

        if (featureFailure == null) {
            return User(email.toString(), name.toString(), phone.toString(), null)
        } else {
            validateError.value = featureFailure
            return null
        }
    }

    private fun handleUserAuthentication(user: User) {
        cacheUserUseCase(CacheUserUseCase.Params(user)) { it.either(::handleFailure, ::userSaved) }
    }

    private fun userSaved(user: User) {
        this.userModel.value = UserModel(user.name, user.email, user.phone, user.token)
    }
}