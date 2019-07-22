package com.shannan.nakollaol.data.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shannan.nakollaol.domain.extension.empty
import com.shannan.nakollaol.domain.interactor.User

@Entity(tableName = "users")
data class UserEntity(@PrimaryKey @ColumnInfo(name = "email") var email: String,
                      @ColumnInfo(name = "name") var name: String,
                      @ColumnInfo(name = "phone") var phone: String?,
                      @ColumnInfo(name = "token") var token: String?) {

    companion object {
        fun empty() = UserEntity(String.empty(), String.empty(), null, null)
    }

    fun toUser() = User(email, name, phone, token)
    fun fromUser(user: User) = UserEntity(user.email, user.name, user.phone, user.token)
}
