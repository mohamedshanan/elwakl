package com.shannan.nakollaol.domain.interactor

import com.shannan.nakollaol.domain.extension.empty

data class Extra(val name: String,
                 val photoUrl: String?,
                 val price: Double) {

    companion object {
        fun empty() = Extra(String.empty(), null, 0.0)
    }
}

