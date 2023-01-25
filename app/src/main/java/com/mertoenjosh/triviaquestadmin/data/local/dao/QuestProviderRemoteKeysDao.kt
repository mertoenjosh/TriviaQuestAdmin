package com.mertoenjosh.triviaquestadmin.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mertoenjosh.triviaquestadmin.data.models.TriviaQuestionRemoteKeys


@Dao
interface QuestProviderRemoteKeysDao {
    @Query("SELECT * FROM remote_keys_table WHERE id =:id")
    suspend fun getRemoteKeys(id: String): TriviaQuestionRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<TriviaQuestionRemoteKeys>)

    @Query("DELETE FROM remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}