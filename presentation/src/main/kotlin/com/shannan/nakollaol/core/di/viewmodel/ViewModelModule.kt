package com.shannan.nakollaol.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shannan.nakollaol.features.binverification.BinVerificationViewModel
import com.shannan.nakollaol.features.codeverification.VerificationViewModel
import com.shannan.nakollaol.features.generateotp.OTPViewModel
import com.shannan.nakollaol.features.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindsSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VerificationViewModel::class)
    abstract fun bindsCodeVerificationViewModel(verificationViewModel: VerificationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BinVerificationViewModel::class)
    abstract fun bindsBinVerificationViewModel(binVerificationViewModel: BinVerificationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OTPViewModel::class)
    abstract fun bindsOTPViewModel(otpViewModel: OTPViewModel): ViewModel
}