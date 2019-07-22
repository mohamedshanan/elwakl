package com.shannan.nakollaol.features.authentication

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.shannan.nakollaol.R
import com.shannan.nakollaol.core.extension.failure
import com.shannan.nakollaol.core.extension.viewModel
import com.shannan.nakollaol.core.platform.BaseFragment
import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.exception.Failure.NetworkConnection
import com.shannan.nakollaol.domain.exception.Failure.ServerError
import kotlinx.android.synthetic.main.fragment_authentication.*

class AuthenticationFragment : BaseFragment() {

    companion object {
        fun newInstance() = AuthenticationFragment()
    }

    private lateinit var authenticationViewModel: AuthenticationViewModel

    override fun layoutId() = R.layout.fragment_authentication

    override fun afterInflation(savedInstanceState: Bundle?) {
        appComponent.inject(this)

        authenticationViewModel = viewModel(viewModelFactory) {
            failure(failure, ::handleFailure)
        }

        authenticationViewModel.validateError.observe(this, Observer<Failure> {
            handleFailure(it)
        })

        authenticationViewModel.userModel.observe(this, Observer<UserModel> {
            if (it != null) {
                success(it)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_signup.setOnClickListener {
            setLoading(true)
            authenticationViewModel.authenticate(input_email, input_name, input_phone)
        }
    }


    private fun success(keyModel: UserModel?) {
        setLoading(false)
        keyModel?.let {
            activity?.let {
                navigator.showOrderScreen(activity as Context)
                it.finish()
            }
        }
    }

    private fun handleFailure(failure: Failure?) {
        setLoading(false)
        when (failure) {
            is NetworkConnection -> {
                notify(R.string.failure_network_connection)
            }
            is ServerError -> {
                notify(R.string.failure_server_error)
            }
            is AuthFailure.InvalidEmail -> {
                input_email.error = resources.getString(R.string.failure_invalid_email)
            }
            is AuthFailure.InvalidName -> {
                input_name.error = resources.getString(R.string.failure_invalid_name)
            }
            is AuthFailure.InvalidPhone -> {
                input_phone.error = resources.getString(R.string.failure_invalid_phone)
            }
        }
    }
}
