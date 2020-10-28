package com.aditya.tictactoe.homepage

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aditya.tictactoe.R
import com.aditya.tictactoe.adapters.SettingsAdapter

class Settings : AppCompatActivity() {

    private lateinit var settingsRecyclerView: RecyclerView
    private lateinit var settingsAdapter: SettingsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        settingsAdapter = SettingsAdapter(this)

        settingsRecyclerView = findViewById(R.id.settings_recycler_view)
        settingsRecyclerView.layoutManager = LinearLayoutManager(this)
        settingsRecyclerView.setHasFixedSize(true)
        settingsRecyclerView.adapter = settingsAdapter

    }
}