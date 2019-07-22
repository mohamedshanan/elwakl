package com.shannan.nakollaol.features.binverification

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
import kotlinx.android.synthetic.main.fragment_bin_verification.*

class BinVerificationFragment : BaseFragment() {

    companion object {
        fun newInstance() = BinVerificationFragment()
    }

    private lateinit var binViewModel: BinVerificationViewModel

    override fun layoutId() = R.layout.fragment_bin_verification

    override fun afterInflation(savedInstanceState: Bundle?) {
        appComponent.inject(this)

        binViewModel = viewModel(viewModelFactory) {
            failure(failure, ::handleFailure)
        }
        binViewModel.binModel.observe(this, Observer<BinModel> {
            openOTPGeneration(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etBin.setOnKeyListener { _, num, _ ->
            if (num > 3) {
                binViewModel.checkBinCode(etBin.text.toString())
            }
            return@setOnKeyListener false
        }
    }

    private fun openOTPGeneration(keyModel: BinModel?) {
        keyModel?.let {
            activity?.let {
                navigator.showVerificationScreen(activity as Context)
            }
        }
    }

    private fun handleFailure(failure: Failure?) {
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
