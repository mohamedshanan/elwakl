package com.shannan.nakollaol.domain.repository

import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.User
import com.shannan.nakollaol.domain.interactor.UseCase

interface UserCache {
    suspend fun saveUser(user: User): Either<Failure, User>
    suspend fun deleteKey(): Either<Failure, UseCase.None>
    suspend fun getUser(): Either<Failure, User>
}
