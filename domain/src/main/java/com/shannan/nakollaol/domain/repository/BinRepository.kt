package com.shannan.nakollaol.domain.repository

import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.savebin.BinCode

interface BinRepository {
    fun saveCode(binCode: String): Either<Failure, BinCode>
    fun checkCode(binCode: String): Either<Failure, BinCode>
}
