package com.aditya.tictactoe.vsfriend

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.aditya.tictactoe.R
import com.aditya.tictactoe.homepage.Dashboard
import com.aditya.tictactoe.homepage.SharedPrefForNightMode
import com.aditya.tictactoe.vsfriend.frienddb.BaseActivity
import com.aditya.tictactoe.vsfriend.frienddb.FriendHistoryDao
import com.aditya.tictactoe.vsfriend.frienddb.FriendHistoryData
import com.aditya.tictactoe.vsfriend.frienddb.FriendHistoryDatabase
import kotlinx.android.synthetic.main.activity_vs_friend.*
import kotlinx.coroutines.launch

class VsFriend : BaseActivity(), View.OnClickListener {

    private lateinit var sharedPrefForNightMode: SharedPrefForNightMode
    private val buttons = Array(3) { arrayOfNulls<Button>(3) }
    private var player1Turn = true
    private var roundCount = 0
    private var player1Points = 0
    private var player2Points = 0
    private var textViewPlayer1: TextView? = null
    private var textViewPlayer2: TextView? = null
    private var p1 = ""
    private var p2 = ""


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        sharedPrefForNightMode = SharedPrefForNightMode(this)
        if (sharedPrefForNightMode.loadNightModeState()) {
            setTheme(R.style.DarkTheme)
        } else setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vs_friend)

        textViewPlayer1 = findViewById(R.id.field_player1)
        textViewPlayer2 = findViewById(R.id.field_player2)

        //DialogBox starts
        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.vsfriend_dialog, null)
        //EditText from dialog box
        val player1 = dialogView.findViewById<EditText>(R.id.player1_name)
        val player2 = dialogView.findViewById<EditText>(R.id.player2_name)
        dialog.setView(dialogView)
        dialog.setCancelable(false)
        dialog.setPositiveButton("Let's Play!!") { _, _ -> }
        dialog.setNegativeButton("Cancel") { _, _ -> }
        val customDialog = dialog.create()
        customDialog.show()
        customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            when {
                player1.text.isBlank() -> Toast.makeText(
                    baseContext,
                    "Please enter player 1 name",
                    Toast.LENGTH_LONG
                ).show()
                player1.length() > 8 -> Toast.makeText(
                    baseContext,
                    "Player 1 name too long!",
                    Toast.LENGTH_LONG
                ).show()
                player2.text.isBlank() -> Toast.makeText(
                    baseContext,
                    "Please enter player 2 name",
                    Toast.LENGTH_LONG
                ).show()
                player2.length() > 8 -> Toast.makeText(
                    baseContext,
                    "Player 2 name too long!",
                    Toast.LENGTH_LONG
                ).show()
                else -> {
                    customDialog.dismiss()
                    p1 = player1.text.toString()
                    p2 = player2.text.toString()
                    field_player1.text = "$p1(X):0"
                    field_player2.text = "$p2(O):0"
                }
            }
        }
        customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            finish()
        }
        //DialogBox Ends

        for (i in 0..2) {
            for (j in 0..2) {
                val buttonID = "button_$i$j"
                val resID = resources.getIdentifier(buttonID, "id", packageName)
                buttons[i][j] = findViewById(resID)
                buttons[i][j]?.setOnClickListener(this)
            }
        }
        val buttonReset = findViewById<Button>(R.id.button_reset_friend)
        buttonReset.setOnClickListener { resetGame() }

        val buttonSaveHistory = findViewById<Button>(R.id.button_save_reset_friend)
        buttonSaveHistory.setOnClickListener {
            launch {
                val friendHistoryData = FriendHistoryData(p1, p2, player1Points, player2Points)

                lateinit var friendHistoryDao: FriendHistoryDao
                val database = applicationContext?.let {
                    FriendHistoryDatabase.getInstance(it)
                }
                if (database != null) {
                    friendHistoryDao = database.friendHistoryDao()
                }
                friendHistoryDao.addHistory(friendHistoryData)
                Toast.makeText(baseContext, "History Saved", Toast.LENGTH_SHORT).show()
            }
            resetGame()
        }

        val buttonSaveAndExit = findViewById<Button>(R.id.button_save_exit_friend)
        buttonSaveAndExit.setOnClickListener {
            launch {
                val friendHistoryData = FriendHistoryData(p1, p2, player1Points, player2Points)

                lateinit var friendHistoryDao: FriendHistoryDao
                val database = applicationContext?.let {
                    FriendHistoryDatabase.getInstance(it)
                }
                if (database != null) {
                    friendHistoryDao = database.friendHistoryDao()
                }
                friendHistoryDao.addHistory(friendHistoryData)
                Toast.makeText(baseContext, "History Saved", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this, Dashboard::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }
    }

    override fun onClick(v: View) {
        if ((v as Button).text.toString() != "") {
            return
        }
        if (player1Turn) {
            v.text = "X"
        } else {
            v.text = "O"
        }
        roundCount++
        if (checkForWin()) {
            if (player1Turn) {
                player1Wins()
            } else {
                player2Wins()
            }
        } else if (roundCount == 9) {
            draw()
        } else {
            player1Turn = !player1Turn
        }
    }

    private fun checkForWin(): Boolean {
        val field = Array(3) { arrayOfNulls<String>(3) }
        for (i in 0..2) {
            for (j in 0..2) {
                field[i][j] = buttons[i][j]!!.text.toString()
            }
        }
        for (i in 0..2) {
            if (field[i][0] == field[i][1] && field[i][0] == field[i][2] && field[i][0] != "") {
                return true
            }
        }
        for (i in 0..2) {
            if (field[0][i] == field[1][i] && field[0][i] == field[2][i] && field[0][i] != "") {
                return true
            }
        }
        if (field[0][0] == field[1][1] && field[0][0] == field[2][2] && field[0][0] != "") {
            return true
        }
        return field[0][2] == field[1][1] && field[0][2] == field[2][0] && field[0][2] != ""
    }

    private fun player1Wins() {
        player1Points++
        Toast.makeText(this, "$p1 won!", Toast.LENGTH_SHORT).show()
        updatePointsText()
        resetBoard()
    }

    private fun player2Wins() {
        player2Points++
        Toast.makeText(this, "$p2 won!", Toast.LENGTH_SHORT).show()
        updatePointsText()
        resetBoard()
    }

    private fun draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show()
        resetBoard()
    }

    @SuppressLint("SetTextI18n")
    private fun updatePointsText() {
        textViewPlayer1!!.text = "$p1(X):$player1Points"
        textViewPlayer2!!.text = "$p2(O):$player2Points"
    }

    private fun resetBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j]!!.text = ""
            }
        }
        roundCount = 0
        player1Turn = true
    }

    @SuppressLint("SetTextI18n")
    private fun resetGame() {
        player1Points = 0
        player2Points = 0
        field_player1.text = "$p1(X):0"
        field_player2.text = "$p2(O):0"
        updatePointsText()
        resetBoard()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("roundCount", roundCount)
        outState.putInt("player1Points", player1Points)
        outState.putInt("player2Points", player2Points)
        outState.putBoolean("player1Turn", player1Turn)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        roundCount = savedInstanceState.getInt("roundCount")
        player1Points = savedInstanceState.getInt("player1Points")
        player2Points = savedInstanceState.getInt("player2Points")
        player1Turn = savedInstanceState.getBoolean("player1Turn")
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
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}

