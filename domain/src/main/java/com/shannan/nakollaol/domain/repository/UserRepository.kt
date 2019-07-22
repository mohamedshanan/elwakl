package com.shannan.nakollaol.domain.repository

import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.User

interface UserRepository {
    fun register(user: User): Either<Failure, User>
    fun login(user: User): Either<Failure, User>
}
