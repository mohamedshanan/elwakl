package com.shannan.nakollaol.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shannan.nakollaol.features.authentication.AuthenticationViewModel
import com.shannan.nakollaol.features.order.OrderViewModel
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
    @ViewModelKey(AuthenticationViewModel::class)
    abstract fun bindsAuthenticationViewModel(authenticationViewModel: AuthenticationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OrderViewModel::class)
    abstract fun bindsOrderViewModel(orderViewModel: OrderViewModel): ViewModel
}