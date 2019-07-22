package com.shannan.nakollaol.data.cache

import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.User
import com.shannan.nakollaol.domain.interactor.UseCase
import com.shannan.nakollaol.domain.repository.UserCache
import javax.inject.Inject

class UserCacheMemoryImpl
@Inject constructor() : UserCache {
    override suspend fun getUser(): Either<Failure, User> {
        return when (user) {
            null -> Either.Left(Failure.ServerError)
            else -> Either.Right(user!!)
        }
    }

    companion object {
        var user: User? = null
    }

    override suspend fun saveUser(user: User): Either<Failure, User> {
        return Either.Right(user)
    }

    override suspend fun deleteKey(): Either<Failure, UseCase.None> {
        user = null
        return Either.Right(UseCase.None())
    }
}