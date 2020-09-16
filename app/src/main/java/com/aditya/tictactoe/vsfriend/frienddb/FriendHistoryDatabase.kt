package com.aditya.tictactoe.vsfriend.frienddb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FriendHistoryData::class], version = 1)
abstract class FriendHistoryDatabase : RoomDatabase() {

    abstract fun friendHistoryDao(): FriendHistoryDao



    companion object {
        @Volatile
        private var instance: FriendHistoryDatabase? = null
        @Synchronized
        fun getInstance(context: Context): FriendHistoryDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    FriendHistoryDatabase::class.java, "friend_history_database"
                ).build()
            }
            return instance
        }
    }


}