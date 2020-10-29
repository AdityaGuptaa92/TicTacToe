package com.aditya.tictactoe.homepage


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aditya.tictactoe.R
import com.aditya.tictactoe.adapters.SettingsAdapter


class Settings : AppCompatActivity() {

    private lateinit var settingsRecyclerView: RecyclerView
    private lateinit var settingsAdapter: SettingsAdapter
    private lateinit var sharedPrefForNightMode: SharedPrefForNightMode

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedPrefForNightMode = SharedPrefForNightMode(this)
        if (sharedPrefForNightMode.loadNightModeState()) {
            setTheme(R.style.DarkTheme)
        } else setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        settingsAdapter = SettingsAdapter(this)
        settingsRecyclerView = findViewById(R.id.settings_recycler_view)
        settingsRecyclerView.layoutManager = LinearLayoutManager(this)
        settingsRecyclerView.setHasFixedSize(true)
        settingsRecyclerView.adapter = settingsAdapter
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this,Dashboard::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }
}