package com.shannan.nakollaol.domain.interactor.generateotp

import com.shannan.nakollaol.domain.extension.empty

data class OTP(val value: String, val lifetime: Long) {

    companion object {
        fun empty() = OTP(String.empty(), Long.MAX_VALUE)
    }
}