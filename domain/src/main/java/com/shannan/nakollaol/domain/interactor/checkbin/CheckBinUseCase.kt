package com.shannan.nakollaol.domain.interactor.checkbin

import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.UseCase
import com.shannan.nakollaol.domain.interactor.savebin.BinCode
import com.shannan.nakollaol.domain.repository.BinRepository
import javax.inject.Inject

class CheckBinUseCase
@Inject constructor(private val binRepository: BinRepository) : UseCase<BinCode, CheckBinUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, BinCode> = binRepository.checkCode(params.binCode)

    data class Params(val binCode: String)
}
