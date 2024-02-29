package com.example.meditation.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.meditation.data.model.Message
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    //异步操作
    @Insert
    suspend fun insertMessage(message: Message)

    //flow保存
    @Query("SELECT * FROM MESSAGE")
    fun getAllMessages(): Flow<List<Message>>

    @Query("DELETE FROM  MESSAGE WHERE id = :id")
    suspend fun deleteMessage(id: Long)
}