package com.aditya.tictactoe.vsfriend

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class PlayerViewModelFactory(private var application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerHistoryViewModel::class.java)) {
            return PlayerHistoryViewModel(application) as T
        }
        throw IllegalArgumentException("ViewModel not found")
    }
}