package com.example.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = UserEntity.TABLE_NAME)
data class UserEntity(
    @PrimaryKey @ColumnInfo(name = UUID) val uuid: UUID,
    @ColumnInfo(name = NAME) val name: String,
    @ColumnInfo(name = EMAIL) val email: String,
    @ColumnInfo(name = AGE) val age: Int,
    @ColumnInfo(name = IMAGE_URL) val imageUrl: String?
) {
    companion object {
        const val TABLE_NAME = "user"
        const val UUID = "uuid"
        const val NAME = "name"
        const val EMAIL = "email"
        const val AGE = "age"
        const val IMAGE_URL = "image_url"
    }
}

data class DeleteUserModel(
    val uuid: UUID
)