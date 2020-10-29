package com.aditya.tictactoe.homepage

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import com.aditya.tictactoe.R
import com.aditya.tictactoe.vsfriend.VsFriend
import com.aditya.tictactoe.vsfriend.VsFriendHistory as VsFriendHistory

class Dashboard : AppCompatActivity() {

    private lateinit var sharedPrefForNightMode: SharedPrefForNightMode

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPrefForNightMode = SharedPrefForNightMode(this)
        if (sharedPrefForNightMode.loadNightModeState()) {
            setTheme(R.style.DarkTheme)
        } else
            setTheme(R.style.AppTheme)


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val buttonVsFriend = findViewById<Button>(R.id.dashboard_vs_friend)
        val buttonPlayerHistory = findViewById<Button>(R.id.dashboard_player_history)
        val buttonSettings = findViewById<Button>(R.id.dashboard_settings)


        buttonVsFriend.setOnClickListener {
            startActivity(Intent(this, VsFriend::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        buttonPlayerHistory.setOnClickListener {
            startActivity(Intent(this, VsFriendHistory::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        buttonSettings.setOnClickListener {
            startActivity(Intent(this, Settings::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

    }

    override fun onRestart() {
        super.onRestart()
        restartActivity()
    }


    private fun restartActivity() {
        finish()
        overridePendingTransition(0, 0)
        startActivity(Intent(this, Dashboard::class.java))
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)    }
}