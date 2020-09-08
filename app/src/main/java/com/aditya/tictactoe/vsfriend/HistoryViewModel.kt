package com.aditya.tictactoe.vsfriend

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class HistoryViewModel : AndroidViewModel {

    private var repository: HistoryRepository
    private var wholeHistory: LiveData<List<History>>

    constructor(application: Application) : super(application) {
        repository = HistoryRepository(application)
        wholeHistory = repository.getWholeHistory()
    }

    fun insert(history: History) {
        repository.insert(history)
    }
     fun update(history: History) {
         repository.update(history)
     }

     fun delete(history: History){
         repository.delete(history)
     }

     fun deleteWholeHistory() = repository.deleteWholeHistory()

     fun getWholeHistory() = wholeHistory



}