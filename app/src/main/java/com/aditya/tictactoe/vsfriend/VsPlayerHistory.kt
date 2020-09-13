package com.aditya.tictactoe.vsfriend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aditya.tictactoe.R

class VsPlayerHistory : AppCompatActivity() {

    private lateinit var playerHistoryViewModel: PlayerHistoryViewModel
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var idTextView: TextView
//    private lateinit var player1TextView: TextView
//    private lateinit var player2TextView: TextView
//    private lateinit var scoreTextView: TextView
//    private lateinit var whoWonTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vs_player_history)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val adapter = PlayerHistoryAdapter()
        recyclerView.adapter = adapter

        val playerViewModelFactory = PlayerViewModelFactory(application)
        playerHistoryViewModel =
            ViewModelProvider(this, playerViewModelFactory).get(PlayerHistoryViewModel::class.java)
        playerHistoryViewModel.getWholeHistory().observe(
            this, Observer<List<PlayerHistory>> { histories -> adapter.setHistory(histories) })

    }
}