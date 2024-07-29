package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.database.dao.UserDao
import com.example.data.database.entities.UserEntity

private const val DATABASE_VERSION = 1
private const val DATABASE_NAME = "users_database.db"

@Database(entities = [UserEntity::class], version = DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Synchronized
        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context = context,
                klass = AppDatabase::class.java,
                name = DATABASE_NAME
            ).build()
        }
    }
}