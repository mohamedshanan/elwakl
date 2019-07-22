
package com.shannan.nakollaol.core.navigation

import com.shannan.nakollaol.AndroidTest
import com.shannan.nakollaol.features.splash.SplashViewModel
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
class NavigatorTest : AndroidTest() {

    private lateinit var navigator: Navigator

    @Mock
    private lateinit var splashViewModel: SplashViewModel

    @Before fun setup() {
        navigator = Navigator()
    }

    @Test fun `should forward user to login screen`() {
        whenever(splashViewModel.isKeyCached())
        navigator.showOTPScreen(activityContext())
    }
}
