
package com.shannan.nakollaol.core.di

import com.shannan.nakollaol.AndroidApplication
import com.shannan.nakollaol.core.di.viewmodel.ViewModelModule
import com.shannan.nakollaol.features.binverification.BinVerificationFragment
import com.shannan.nakollaol.features.codeverification.CodeVerificationActivity
import com.shannan.nakollaol.features.codeverification.CodeVerificationFragment
import com.shannan.nakollaol.features.generateotp.OTPActivity
import com.shannan.nakollaol.features.generateotp.OTPFragment
import com.shannan.nakollaol.features.splash.SplashActivity
import com.shannan.nakollaol.features.splash.SplashFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(splashActivity: SplashActivity)
    fun inject(codeVerificationActivity: CodeVerificationActivity)
    fun inject(otpActivity: OTPActivity)

    fun inject(splashFragment: SplashFragment)
    fun inject(binVerificationFragment: BinVerificationFragment)
    fun inject(codeVerificationFragment: CodeVerificationFragment)
    fun inject(otpFragment: OTPFragment)

}
