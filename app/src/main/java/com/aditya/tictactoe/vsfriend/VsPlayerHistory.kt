package com.aditya.tictactoe.vsfriend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aditya.tictactoe.R
import org.w3c.dom.Text

class VsPlayerHistory : AppCompatActivity() {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var recyclerView : RecyclerView
    private lateinit var player1TextView: TextView
    private lateinit var player2TextView: TextView
    private lateinit var scoreTextView: TextView
    private lateinit var whoWonTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vs_player_history)

    }
}