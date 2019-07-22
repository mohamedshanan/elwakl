package com.shannan.nakollaol.data.cache

import com.shannan.nakollaol.data.Entity.UserEntity
import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.User
import com.shannan.nakollaol.domain.interactor.UseCase
import com.shannan.nakollaol.domain.repository.UserCache
import javax.inject.Inject

class UserCacheRoomImpl
@Inject constructor(private val userDao: UserDao) : UserCache {

    override suspend fun getUser(): Either<Failure, User> {
        val userList = userDao.getUsers()
        return if (userList.isNullOrEmpty()) {
            Either.Left(Failure.ServerError)
        } else {
            Either.Right(userList[0].toUser())
        }
    }

    override suspend fun saveUser(user: User): Either<Failure, User> {
        return when (userDao.insert(UserEntity.empty().fromUser(user))) {
            in Long.MIN_VALUE..0 -> {
                Either.Left(Failure.ServerError)
            }
            else -> Either.Right(user)
        }
    }

    override suspend fun deleteKey(): Either<Failure, UseCase.None> {
        return when (userDao.deleteAll()) {
            in Int.MIN_VALUE..0 -> {
                Either.Left(Failure.ServerError)
            }
            else -> Either.Right(UseCase.None())
        }
    }
}