package com.aditya.tictactoe.vsfriend

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class PlayerHistoryViewModel(application: Application) : AndroidViewModel(application) {

    private var repositoryPlayer: PlayerHistoryRepository = PlayerHistoryRepository(application)
    private var wholePlayerHistory: LiveData<List<PlayerHistory>> =
        repositoryPlayer.getWholeHistory()

    fun insert(playerHistory: PlayerHistory) = repositoryPlayer.insert(playerHistory)

    fun update(playerHistory: PlayerHistory) = repositoryPlayer.update(playerHistory)

    fun delete(playerHistory: PlayerHistory) = repositoryPlayer.delete(playerHistory)

    fun deleteWholeHistory() = repositoryPlayer.deleteWholeHistory()

    fun getWholeHistory() = wholePlayerHistory


}