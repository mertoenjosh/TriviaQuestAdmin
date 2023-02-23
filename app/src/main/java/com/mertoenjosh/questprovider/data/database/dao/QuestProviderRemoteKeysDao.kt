package com.mertoenjosh.questprovider.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mertoenjosh.questprovider.data.database.models.QuestionRemoteKeysEntity


@Dao
interface QuestProviderRemoteKeysDao {
    @Query("SELECT * FROM tbl_remote_keys WHERE id = :id")
    suspend fun getRemoteKeys(id: String): QuestionRemoteKeysEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<QuestionRemoteKeysEntity>)

    @Query("DELETE FROM tbl_remote_keys")
    suspend fun deleteAllRemoteKeys()
}