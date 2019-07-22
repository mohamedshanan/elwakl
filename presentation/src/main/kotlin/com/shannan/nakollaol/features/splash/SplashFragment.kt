package com.shannan.nakollaol.features.splash

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.shannan.nakollaol.R
import com.shannan.nakollaol.core.extension.failure
import com.shannan.nakollaol.core.extension.viewModel
import com.shannan.nakollaol.core.platform.BaseFragment
import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.interactor.User
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment : BaseFragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private lateinit var splashViewModel: SplashViewModel

    override fun layoutId() = R.layout.fragment_splash

    override fun afterInflation(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        splashViewModel = viewModel(viewModelFactory) {
            failure(failure, ::handleFailure)
        }
        splashViewModel.cachedUser.observe(this, Observer<User> {
            if (it != null) {
                onSuccess()
            } else {
                handleFailure(Failure.ServerError)
            }
        })
        splashViewModel.progress.observe(this, Observer<Int> {
            progress.visibility = it!!
        })

        checkCachedKey()
    }


    private fun checkCachedKey() {
        Handler().postDelayed({
            splashViewModel.isKeyCached()
        }, 2000)
    }

    private fun onSuccess() {
        navigator.showOTPScreen(activity as Context)
        activity?.finish()
    }

    private fun handleFailure(failure: Failure?) {
        splashViewModel.progress.value = View.INVISIBLE
        navigator.showVerificationScreen(activity as Context)
        activity?.finish()
    }
}