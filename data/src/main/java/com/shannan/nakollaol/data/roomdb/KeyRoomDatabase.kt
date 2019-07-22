package com.shannan.nakollaol.data.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shannan.nakollaol.data.Entity.UserEntity
import com.shannan.nakollaol.data.cache.UserDao

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class KeyRoomDatabase : RoomDatabase() {

    abstract fun keyDao(): UserDao
}