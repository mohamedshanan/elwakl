package com.shannan.nakollaol.data.net

import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.savebin.BinCode
import com.shannan.nakollaol.domain.repository.BinRepository
import javax.inject.Inject

class FakeBinRepositoryImpl
@Inject constructor() : BinRepository {
    override fun saveCode(binCode: String): Either<Failure, BinCode> {
        return Either.Right(BinCode(binCode))
    }

    override fun checkCode(binCode: String): Either<Failure, BinCode> {
        return Either.Right(BinCode(binCode))
    }
}