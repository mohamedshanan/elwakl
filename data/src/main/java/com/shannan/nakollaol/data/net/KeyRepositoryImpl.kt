package com.shannan.nakollaol.data.net

import com.shannan.nakollaol.data.Entity.UserEntity
import com.shannan.nakollaol.data.NetworkHandler
import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.User
import com.shannan.nakollaol.domain.repository.KeyRepository
import retrofit2.Call
import javax.inject.Inject

class KeyRepositoryImpl
@Inject constructor(private val networkHandler: NetworkHandler,
                    private val service: KeysService) : KeyRepository {

    override fun verifyCode(verificationCode: String): Either<Failure, User> {
        return when (networkHandler.isConnected) {
            true ->  Either.Right(User.empty())
            false, null -> Either.Left(Failure.NetworkConnection)
        }
    }
}