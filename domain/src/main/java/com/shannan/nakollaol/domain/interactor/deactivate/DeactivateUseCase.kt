package com.shannan.nakollaol.domain.interactor.deactivate

import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.UseCase
import com.shannan.nakollaol.domain.repository.UserCache
import javax.inject.Inject

class DeactivateUseCase
@Inject constructor(private val userCache: UserCache) : UseCase<UseCase.None, DeactivateUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, None> = userCache.deleteKey()

    data class Params(val none: None)
}
