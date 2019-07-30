package com.shannan.nakollaol.domain.interactor

import com.shannan.nakollaol.domain.extension.empty

data class Restaurant(val id: Int,
                      val name: String,
                      val photoUrl: String?,
                      val phone: String?,
                      val deliveryCost: Double,
                      val sandwichs: List<Sandwich>) {

    companion object {
        fun empty() = Restaurant(Int.MAX_VALUE, String.empty(), null, null, 0.0, emptyList<Sandwich>())
    }
}

