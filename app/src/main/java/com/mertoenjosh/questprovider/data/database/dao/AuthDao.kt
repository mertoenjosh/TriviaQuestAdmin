package com.mertoenjosh.questprovider.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mertoenjosh.questprovider.data.database.models.UserEntity

@Dao
interface AuthDao {
    @Query("SELECT * FROM tbl_users WHERE id = :id")
    fun getUserWithId(id: String): UserEntity?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)
    @Query("DELETE FROM tbl_users")
    suspend fun deleteUser()
}