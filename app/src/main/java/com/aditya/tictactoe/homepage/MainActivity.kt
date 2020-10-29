package com.aditya.tictactoe.homepage

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.aditya.tictactoe.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //to hide status bar
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)

        val imageView: ImageView = findViewById(R.id.imageView)
        val textView: TextView = findViewById(R.id.textView)
        // implementing animation to both ImageView and TextView
        val topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anim)
        val bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim)
        imageView.startAnimation(topAnim)
        textView.startAnimation(bottomAnim)

        /*Here we are implementing the splash screen with the help of Handler() and then after the
        splash screen is gone i.e. after 2.5 secs the next activity called is VsFriend.kt*/
        Handler(Looper.myLooper()!!).postDelayed({
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
            finish()
        }, 2500)

    }


}
