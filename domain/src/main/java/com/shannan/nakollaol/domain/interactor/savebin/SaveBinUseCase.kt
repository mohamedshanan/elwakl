package com.shannan.nakollaol.domain.interactor.savebin

import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.UseCase
import com.shannan.nakollaol.domain.repository.BinRepository
import javax.inject.Inject

class SaveBinUseCase
@Inject constructor(private val binRepository: BinRepository) : UseCase<BinCode, SaveBinUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, BinCode> = binRepository.saveCode(params.binCode)

    data class Params(val binCode: String)
}
