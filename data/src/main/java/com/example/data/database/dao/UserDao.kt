package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.entities.DeleteUserModel
import com.example.data.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface UserDao {
    @Query("SELECT * from ${UserEntity.TABLE_NAME}")
    fun getUsers(): Flow<List<UserEntity>>

    @Query("SELECT * from ${UserEntity.TABLE_NAME} users " +
            "WHERE users.${UserEntity.UUID} == :uuid")
    suspend fun getUserByUUID(uuid: UUID): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Delete(entity = UserEntity::class)
    suspend fun deleteUser(deleteUserModel: DeleteUserModel)
}