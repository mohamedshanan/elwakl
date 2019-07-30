package com.shannan.nakollaol.domain.interactor

import com.shannan.nakollaol.domain.extension.empty

data class Sandwich(val name: String,
                    val photoUrl: String?,
                    val price: Double,
                    val extras: List<Extra>) {

    companion object {
        fun empty() = Sandwich(String.empty(), null, 0.0, emptyList())
    }
}

