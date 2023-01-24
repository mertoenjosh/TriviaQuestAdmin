package com.mertoenjosh.triviaquestadmin.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mertoenjosh.triviaquestadmin.data.models.QuestProviderRemoteKeys


@Dao
interface QuestProviderRemoteKeysDao {
    @Query("SELECT * FROM remote_keys_table WHERE id =:id")
    suspend fun getRemoteKeys(id: String): QuestProviderRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<QuestProviderRemoteKeys>)

    @Query("DELETE FROM remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}