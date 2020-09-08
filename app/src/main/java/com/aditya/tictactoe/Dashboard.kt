package com.aditya.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.aditya.tictactoe.vscomputer.VsComputer
import com.aditya.tictactoe.vsfriend.VsFriend

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val vsComputer = findViewById<Button>(R.id.vs_computer)
        val vsFriend =  findViewById<Button>(R.id.vs_friend)

        vsComputer.setOnClickListener {
            val intent = Intent(this, VsComputer::class.java)
            startActivity(intent)
        }

        vsFriend.setOnClickListener {
            val intent = Intent(this, VsFriend::class.java)
            startActivity(intent)
        }
    }
}