package com.aditya.tictactoe.vsfriend

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class HistoryRepository {

    private lateinit var historyDao: HistoryDao

    private lateinit var wholeHistory: LiveData<List<History>>

    constructor(application: Application) {
        val database: HistoryDatabase? = HistoryDatabase.getInstance(application)
        if (database != null) {
            historyDao = database.historyDao()
        }
        wholeHistory = historyDao.getWholeHistory()
    }

    fun insert(history: History) {
        InsertHistoryAsyncTask(historyDao).execute(history)
    }

    fun update(history: History) {
        UpdateHistoryAsyncTask(historyDao).execute(history)
    }

    fun delete(history: History) {
        DeleteAllHistoryAsyncTask(historyDao).execute(history)

    }

    fun deleteWholeHistory() {
        DeleteAllHistoryAsyncTask(historyDao).execute()


    }

    fun getWholeHistory(): LiveData<List<History>> = wholeHistory

    companion object {
        private class InsertHistoryAsyncTask : AsyncTask<History, Void, Void> {
            private var historyDao: HistoryDao

            constructor(historyDao: HistoryDao) {
                this.historyDao = historyDao
            }
            override fun doInBackground(vararg histories: History): Void? {
                historyDao.insert(histories[0])
                return null
            }

        }

        private class UpdateHistoryAsyncTask : AsyncTask<History, Void, Void> {
            private var historyDao: HistoryDao

            constructor(historyDao: HistoryDao) {
                this.historyDao = historyDao
            }
            override fun doInBackground(vararg histories: History): Void? {
                historyDao.update(histories[0])
                return null
            }

        }

        private class DeleteHistoryAsyncTask : AsyncTask<History, Void, Void> {
            private var historyDao: HistoryDao

            constructor(historyDao: HistoryDao) {
                this.historyDao = historyDao
            }
            override fun doInBackground(vararg histories: History): Void? {
                historyDao.delete(histories[0])
                return null
            }

        }

        private class DeleteAllHistoryAsyncTask : AsyncTask<History, Void, Void> {
            private var historyDao: HistoryDao

            constructor(historyDao: HistoryDao) {
                this.historyDao = historyDao
            }
            override fun doInBackground(vararg histories: History): Void? {
                historyDao.deleteWholeHistory()
                return null
            }

        }

    }


}