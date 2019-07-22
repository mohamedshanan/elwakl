package com.shannan.nakollaol.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shannan.nakollaol.data.Entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * from users LIMIT 1")
    fun getUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: UserEntity): Long

    @Query("DELETE FROM users")
    fun deleteAll(): Int
}