package com.shannan.nakollaol.features.generateotp

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import com.shannan.nakollaol.R
import com.shannan.nakollaol.core.extension.copyTextToClipBoard
import com.shannan.nakollaol.core.extension.failure
import com.shannan.nakollaol.core.extension.viewModel
import com.shannan.nakollaol.core.platform.BaseFragment
import com.shannan.nakollaol.domain.exception.CodeVerificationFailure
import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.exception.Failure.NetworkConnection
import com.shannan.nakollaol.domain.exception.Failure.ServerError
import kotlinx.android.synthetic.main.fragment_otp_generation.*
import kotlinx.android.synthetic.main.layout_otp.*

class OTPFragment : BaseFragment() {

    companion object {
        fun newInstance() = OTPFragment()
    }

    private lateinit var otpViewModel: OTPViewModel

    override fun layoutId() = R.layout.fragment_otp_generation

    override fun afterInflation(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        appComponent.inject(this)

        otpViewModel = viewModel(viewModelFactory) {
            failure(failure, ::handleFailure)
        }

        otpViewModel.otpModel.observe(this, Observer<OTPModel> {
            success(it)
        })

        otpViewModel.keyActive.observe(this, Observer<Boolean> {
            handleDeactivate(it)
        })

//        otpViewModel.getLifeTime().observe(this, Observer<Long> {
//            arcProgress.max = it.toInt() / 1000
//            arcProgress.progress = it.toInt() / 1000
//        })
//
//        otpViewModel.getElapsedTime().observe(this, Observer<Long> {
//
//            arcProgress.progress = it.toInt()
//
//            if (it < 16) {
//                activity?.let { it1 -> ContextCompat.getColor(it1, R.color.colorPrimaryDark) }?.let { it2 -> arcProgress.textColor = it2 }
//            }
//
//            if (it == 0L) {
//                Handler().postDelayed({
//                    startLayout.visibility = VISIBLE
//                    otpLayout.visibility = GONE
//                    btnGenerate.isEnabled = true
//                }, 1000)
//            }
//        })
    }

    private fun handleDeactivate(it: Boolean?) {
        when (it) {
            true -> {
                navigator.showVerificationScreen(activity as Context)
                activity?.finish()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnGenerate.setOnClickListener {
            btnGenerate.isEnabled = false
            otpViewModel.getOTP()
        }
    }

    private fun success(otpModel: OTPModel?) {
        otpModel?.let {
            otpLayout.visibility = VISIBLE
            startLayout.visibility = GONE
            txtCode.text = it.value
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_otp, menu)
    }

    // actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_copy -> {
            txtCode.copyTextToClipBoard()
            notify(R.string.code_copied)
            true
        }
        R.id.action_deactivate -> {
            setErrorDialog(R.string.dialog_deactivate_message,
                    R.string.dialog_deactivate_positive_text,
                    R.string.dialog_deactivate_negative_text,
                    { otpViewModel.deactivate() }, {})
            true
        }
        else -> true
    }

    private fun handleFailure(failure: Failure?) {
        btnGenerate.isEnabled = true
        when (failure) {
            is NetworkConnection -> {
                notify(R.string.failure_network_connection)
                activity?.finish()
            }
            is ServerError -> {
                notify(R.string.failure_server_error)
                activity?.finish()
            }
            is CodeVerificationFailure.InvalidVerificationCode -> {
                notify(R.string.failure_invalid_verification_code)
                activity?.finish()
            }
        }
    }
}