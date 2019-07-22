package com.shannan.nakollaol.domain.interactor.savebin

import com.shannan.nakollaol.domain.extension.empty

data class BinCode(val binCode: String) {

    companion object {
        fun empty() = BinCode(String.empty())
    }
}