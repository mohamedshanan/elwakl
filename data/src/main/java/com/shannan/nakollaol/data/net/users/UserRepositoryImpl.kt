package com.shannan.nakollaol.data.net.users

import com.shannan.nakollaol.data.NetworkHandler
import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.User
import com.shannan.nakollaol.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl
@Inject constructor(private val networkHandler: NetworkHandler,
                    private val service: KeysService) : UserRepository {

    override fun register(user: User): Either<Failure, User> {
        return when (networkHandler.isConnected) {
            true -> Either.Right(user)
            false, null -> Either.Left(Failure.NetworkConnection)
        }
    }

    override fun login(user: User): Either<Failure, User> {
        return when (networkHandler.isConnected) {
            true -> Either.Right(user)
            false, null -> Either.Left(Failure.NetworkConnection)
        }
    }
}