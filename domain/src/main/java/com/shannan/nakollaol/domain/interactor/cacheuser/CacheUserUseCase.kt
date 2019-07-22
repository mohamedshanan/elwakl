package com.shannan.nakollaol.domain.interactor.cacheuser

import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.User
import com.shannan.nakollaol.domain.interactor.UseCase
import com.shannan.nakollaol.domain.repository.UserCache
import javax.inject.Inject

class CacheUserUseCase
@Inject constructor(private val userCache: UserCache) : UseCase<User, CacheUserUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, User> = userCache.saveUser(params.user)

    data class Params(val user: User)
}
