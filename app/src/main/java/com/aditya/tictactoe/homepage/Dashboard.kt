package com.aditya.tictactoe.homepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.aditya.tictactoe.R
import com.aditya.tictactoe.vscomputer.VsComputer
import com.aditya.tictactoe.vsfriend.VsFriend
import com.aditya.tictactoe.vsfriend.VsFriendHistory

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val buttonVsComputer = findViewById<Button>(R.id.button_vs_computer)
        val buttonVsFriend = findViewById<Button>(R.id.button_vs_friend)
        val buttonPlayerHistory = findViewById<Button>(R.id.button_player_history)

        buttonVsComputer.setOnClickListener {
            val intent = Intent(this, VsComputer::class.java)
            startActivity(intent)
        }

        buttonVsFriend.setOnClickListener {
            val intent = Intent(this, VsFriend::class.java)
            startActivity(intent)
        }

        buttonPlayerHistory.setOnClickListener {
            val intent = Intent(this, VsFriendHistory::class.java)
            startActivity(intent)
        }
    }
}