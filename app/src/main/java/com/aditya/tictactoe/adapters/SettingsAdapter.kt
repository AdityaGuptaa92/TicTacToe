package com.aditya.tictactoe.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aditya.tictactoe.R
import com.aditya.tictactoe.homepage.Settings
import com.aditya.tictactoe.homepage.SharedPrefForNightMode


class SettingsAdapter(private val mContext: Context) :
    RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {


    private var sharedPrefForNightMode = SharedPrefForNightMode(mContext)
    private val settingsTitles = arrayOf("Theme", "Contact Us", "App Info")
    private val settingsIcons =
        arrayOf(R.drawable.ic_brightness96, R.drawable.ic_contact96, R.drawable.ic_info96)


    class SettingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val settingsIcon: ImageView = itemView.findViewById(R.id.settings_icon)
        val settingsItemName: TextView = itemView.findViewById(R.id.settings_item_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.settings_items, parent, false)
        return SettingsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        holder.settingsIcon.setImageResource(settingsIcons[position])
        holder.settingsItemName.text = settingsTitles[position]

        holder.settingsIcon.setOnClickListener {
            when (settingsIcons[position]) {
                R.drawable.ic_brightness96 -> theme(mContext)
                R.drawable.ic_contact96 -> contactUs(mContext)
                R.drawable.ic_info96 -> appInfo(mContext)
            }
        }
    }

    override fun getItemCount() = settingsTitles.size

    private fun appInfo(context: Context) {
        val appInfoDialog = Dialog(context)
        appInfoDialog.setContentView(R.layout.app_info_popup)
        appInfoDialog.show()
        val closeAppInfo = appInfoDialog.findViewById<Button>(R.id.close_app_info)
        closeAppInfo.setOnClickListener {
            appInfoDialog.dismiss()
        }
    }

    private fun contactUs(context: Context) {
        val contactUsDialog = Dialog(context)
        contactUsDialog.setContentView(R.layout.contactus_popup)
        contactUsDialog.show()
        val githubLink = contactUsDialog.findViewById<ImageView>(R.id.github_link)
        val mailLink = contactUsDialog.findViewById<ImageView>(R.id.mail_link)
        val linkedInLink = contactUsDialog.findViewById<ImageView>(R.id.linked_link)
        val closeContactUs = contactUsDialog.findViewById<Button>(R.id.close_contactus)
        closeContactUs.setOnClickListener {
            contactUsDialog.dismiss()
        }

        githubLink.setOnClickListener {
            val urlGithub = "https://github.com/AdityaGuptaa92/TicTacToe"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlGithub))
            context.startActivity(intent)
        }

        mailLink.setOnClickListener {
            val urlMail = "adityagupta3214@gmail.com"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:$urlMail"))
            context.startActivity(intent)
        }

        linkedInLink.setOnClickListener {
            val urlLinkedIn = "https://www.linkedin.com/in/aditya-gupta-646220191"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlLinkedIn))
            context.startActivity(intent)
        }
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private fun theme(context: Context) {
        val themeDialog = Dialog(context)
        themeDialog.setContentView(R.layout.theme_popup)
        themeDialog.setCancelable(false)
        themeDialog.show()
        val themeTitle = themeDialog.findViewById<TextView>(R.id.theme_title)
        val closeTheme = themeDialog.findViewById<Button>(R.id.close_theme)
        closeTheme.setOnClickListener {
            themeDialog.dismiss()
        }
        val switch = themeDialog.findViewById<Switch>(R.id.dark_mode_switch)
        if(sharedPrefForNightMode.loadNightModeState()) {
            switch.isChecked = true
            themeTitle.text = "Disable Night Mode"
        }
        switch.setOnCheckedChangeListener { _, _ ->
            if (switch.isChecked) {
                sharedPrefForNightMode.setNightModeState(true)
                themeTitle.text = "Disable Night Mode"
                restartSettings()
            } else {
                sharedPrefForNightMode.setNightModeState(false)
                themeTitle.text = "Enable Night Mode"
                restartSettings()
            }
        }
    }

    private fun restartSettings() {
        mContext.startActivity(Intent(mContext, Settings::class.java))
        (mContext as Activity).overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        mContext.finish()
        mContext.overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }
}