
package com.shannan.nakollaol.core.navigation

import android.content.Context
import com.shannan.nakollaol.features.binverification.BinVerificationActivity
import com.shannan.nakollaol.features.codeverification.CodeVerificationActivity
import com.shannan.nakollaol.features.generateotp.OTPActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator
@Inject constructor() {

    fun showVerificationScreen(context: Context) = context.startActivity(CodeVerificationActivity.callingIntent(context))

    fun showBinScreen(context: Context) = context.startActivity(BinVerificationActivity.callingIntent(context))

    fun showOTPScreen(context: Context) = context.startActivity(OTPActivity.callingIntent(context))
}


