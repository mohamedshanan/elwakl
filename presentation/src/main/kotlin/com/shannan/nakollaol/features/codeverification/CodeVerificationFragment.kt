package com.shannan.nakollaol.features.codeverification

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.shannan.nakollaol.R
import com.shannan.nakollaol.core.extension.close
import com.shannan.nakollaol.core.extension.failure
import com.shannan.nakollaol.core.extension.viewModel
import com.shannan.nakollaol.core.platform.BaseFragment
import com.shannan.nakollaol.domain.exception.CodeVerificationFailure
import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.exception.Failure.NetworkConnection
import com.shannan.nakollaol.domain.exception.Failure.ServerError
import kotlinx.android.synthetic.main.fragment_code_verification.*

class CodeVerificationFragment : BaseFragment() {

    companion object {
        fun newInstance() = CodeVerificationFragment()
    }

    private lateinit var verificationViewModel: VerificationViewModel

    override fun layoutId() = R.layout.fragment_code_verification

    override fun afterInflation(savedInstanceState: Bundle?) {
        appComponent.inject(this)

        verificationViewModel = viewModel(viewModelFactory) {
            failure(failure, ::handleFailure)
        }

        verificationViewModel.userModel.observe(this, Observer<UserModel> {
            success(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnVerify.setOnClickListener {
            if (!etVerificationCode.text.isNullOrEmpty()) {
                setLoading(true)
                verificationViewModel.verifyCode(etVerificationCode.text.toString())
            } else {
                etVerificationCode.error = resources.getString(R.string.verification_code_error)
            }
        }
    }

    private fun success(keyModel: UserModel?) {
        setLoading(false)
        keyModel?.let {
            activity?.let {
                navigator.showOTPScreen(activity as Context)
                it.finish()
            }
        }
    }

    private fun handleFailure(failure: Failure?) {
        setLoading(false)
        when (failure) {
            is NetworkConnection -> {
                notify(R.string.failure_network_connection); close()
            }
            is ServerError -> {
                notify(R.string.failure_server_error); close()
            }
            is CodeVerificationFailure.InvalidVerificationCode -> {
                notify(R.string.failure_invalid_verification_code); close()
            }
        }
    }
}
