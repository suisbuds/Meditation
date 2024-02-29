package com.example.meditation.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.meditation.data.dao.MessageDao
import com.example.meditation.data.model.Message

@Database(entities = [Message::class], version = 1)

abstract class MessageDatabase : RoomDatabase() {
    abstract val messageDao: MessageDao
}

object DatabaseProvider {
    private var db: MessageDatabase? = null
    fun getDatabase(context: Context): MessageDatabase {
        return db ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                MessageDatabase::class.java,
                "MessageDatabase"
            ).build()
            db = instance
            instance
        }
    }
}