package com.shannan.nakollaol.domain.interactor.getcacheduser

import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.UseCase
import com.shannan.nakollaol.domain.interactor.User
import com.shannan.nakollaol.domain.repository.UserCache
import javax.inject.Inject

class GetCachedUserUseCase
@Inject constructor(private val cache: UserCache) : UseCase<User, GetCachedUserUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, User> = cache.getUser()

    data class Params(val none: None)
}
