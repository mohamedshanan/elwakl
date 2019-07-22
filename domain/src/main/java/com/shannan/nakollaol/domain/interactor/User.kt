package com.shannan.nakollaol.domain.interactor

import com.shannan.nakollaol.domain.extension.empty

data class User(var email: String, var name: String, var phone: String?, var token: String?) {

    companion object {
        fun empty() = User(String.empty(), String.empty(), null, null)
    }
}
