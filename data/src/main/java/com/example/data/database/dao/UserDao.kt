package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.database.entities.DeleteUserModel
import com.example.data.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface UserDao {
    @Query("SELECT * from ${UserEntity.TABLE_NAME}")
    fun get(): Flow<List<UserEntity>>

    @Query("SELECT * from ${UserEntity.TABLE_NAME} users " +
            "WHERE users.${UserEntity.UUID} == :uuid")
    fun getByUUID(uuid: UUID): Flow<UserEntity>

    @Update
    suspend fun update(userEntity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: List<UserEntity>)

    @Delete(entity = UserEntity::class)
    suspend fun delete(deleteUserModel: DeleteUserModel)
}