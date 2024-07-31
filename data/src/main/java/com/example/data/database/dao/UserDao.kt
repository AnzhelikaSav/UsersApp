package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.entities.UserEntity
import java.util.UUID

@Dao
interface UserDao {
    @Query("SELECT * from ${UserEntity.TABLE_NAME}")
    suspend fun getUsers(): List<UserEntity>

    @Query("SELECT * from ${UserEntity.TABLE_NAME} users " +
            "WHERE users.${UserEntity.UUID} == :uuid")
    suspend fun getUserByUUID(uuid: UUID): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)
}