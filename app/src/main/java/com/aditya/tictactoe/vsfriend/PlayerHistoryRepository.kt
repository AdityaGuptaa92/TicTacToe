package com.aditya.tictactoe.vsfriend

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class PlayerHistoryRepository(application: Application) {

    private lateinit var playerHistoryDao: PlayerHistoryDao

    private var wholePlayerHistory: LiveData<List<PlayerHistory>>

    init {
        val databasePlayer: PlayerHistoryDatabase? = PlayerHistoryDatabase.getInstance(application)
        if (databasePlayer != null) {
            playerHistoryDao = databasePlayer.historyDao()
        }
        wholePlayerHistory = playerHistoryDao.getWholeHistory()
    }

    fun insert(playerHistory: PlayerHistory) {
        InsertHistoryAsyncTask(playerHistoryDao).execute(playerHistory)
    }

    fun update(playerHistory: PlayerHistory) {
        UpdateHistoryAsyncTask(playerHistoryDao).execute(playerHistory)
    }

    fun delete(playerHistory: PlayerHistory) {
        DeleteHistoryAsyncTask(playerHistoryDao).execute(playerHistory)

    }

    fun deleteWholeHistory() {
        DeleteAllHistoryAsyncTask(playerHistoryDao).execute()


    }

    fun getWholeHistory(): LiveData<List<PlayerHistory>> = wholePlayerHistory

    private class InsertHistoryAsyncTask(private var playerHistoryDao: PlayerHistoryDao) :
        AsyncTask<PlayerHistory, Void, Void>() {
        override fun doInBackground(vararg playerHistories: PlayerHistory): Void? {
            playerHistoryDao.insert(playerHistories[0])
            return null
        }

    }

    private class UpdateHistoryAsyncTask(private var playerHistoryDao: PlayerHistoryDao) :
        AsyncTask<PlayerHistory, Void, Void>() {
        override fun doInBackground(vararg playerHistories: PlayerHistory): Void? {
            playerHistoryDao.update(playerHistories[0])
            return null
        }

    }

    private class DeleteHistoryAsyncTask(private var playerHistoryDao: PlayerHistoryDao) :
        AsyncTask<PlayerHistory, Void, Void>() {
        override fun doInBackground(vararg playerHistories: PlayerHistory): Void? {
            playerHistoryDao.delete(playerHistories[0])
            return null
        }

    }

    private class DeleteAllHistoryAsyncTask(private var playerHistoryDao: PlayerHistoryDao) :
        AsyncTask<PlayerHistory, Void, Void>() {
        override fun doInBackground(vararg playerHistories: PlayerHistory): Void? {
            playerHistoryDao.deleteWholeHistory()
            return null
        }

    }


}