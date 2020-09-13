package com.aditya.tictactoe.vsfriend

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [PlayerHistory::class], version = 1)
abstract class PlayerHistoryDatabase : RoomDatabase() {

    abstract fun historyDao(): PlayerHistoryDao

    companion object {
        @Volatile
        private var instance: PlayerHistoryDatabase? = null

        @Synchronized
        fun getInstance(context: Context): PlayerHistoryDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlayerHistoryDatabase::class.java, "history_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            }
            return instance
        }

        private val roomCallback: Callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                instance?.let { PopulateDbAsyncTask(it).execute() }
            }
        }

        private class PopulateDbAsyncTask(db: PlayerHistoryDatabase) :
            AsyncTask<Void, Void, Void>() {
            private val playerHistoryDao: PlayerHistoryDao = db.historyDao()

            override fun doInBackground(vararg voids: Void?): Void? {
                playerHistoryDao.insert(PlayerHistory(1,"Aditya", "Adi","Score: 1-1", "Draw"))
                playerHistoryDao.insert(PlayerHistory(2,"Aditya", "John","Score:2-0","Aditya"))
                playerHistoryDao.insert(PlayerHistory(3,"Mark","Seth","Score: 5-8","Seth"))
                return null
            }
        }


    }


}