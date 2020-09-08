package com.aditya.tictactoe.vscomputer

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.aditya.tictactoe.Dashboard
import com.aditya.tictactoe.R


private val buttons = Array(3) { arrayOfNulls<Button>(3) }
private var cells = mutableListOf<Button>()

class VsComputer : AppCompatActivity(), View.OnClickListener {

    private var roundCount = 0
    private var playerPoints = 0
    private var computerPoints = 0
    private lateinit var playerNameDialog: EditText
    private lateinit var computer: TextView
    private lateinit var playerName: TextView
    private lateinit var player: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vs_computer)

        playerName = findViewById(R.id.field_player)
        computer = findViewById(R.id.field_computer)

        // DialogBox starts
        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.vscomputer_dialog, null)
        playerNameDialog = dialogView.findViewById(R.id.player_name)
        dialog.setView(dialogView)
        dialog.setPositiveButton("Let's Play!!") { _: DialogInterface, _: Int -> }
        dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int -> }
        val customDialog = dialog.create()
        customDialog.show()
        customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            when {
                playerNameDialog.text.isBlank() -> Toast.makeText(
                    this,
                    "Enter X player name",
                    Toast.LENGTH_LONG
                ).show()
                playerNameDialog.length() > 8 -> Toast.makeText(
                    this,
                    "Player name too long!!",
                    Toast.LENGTH_LONG
                ).show()

                else -> {
                    customDialog.dismiss()
                    player = playerNameDialog.text.toString()
                    playerName.text = "$player(X):0"
                    computer.text = "Computer(O):0"

                }
            }
        }

        customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        // DialogBox ends

        for (i in 0..2) {
            for (j in 0..2) {
                val buttonID = "button_$i$j"
                val resID = resources.getIdentifier(buttonID, "id", packageName)
                buttons[i][j] = findViewById(resID)
                buttons[i][j]?.setOnClickListener(this)
            }
        }
        val buttonReset = findViewById<Button>(R.id.button_reset_computer)
        buttonReset.setOnClickListener { resetGame() }

        val buttonSave = findViewById<Button>(R.id.button_save_computer)
        buttonSave.setOnClickListener { }

    }

    override fun onClick(v: View?) {
        if ((v as Button).text.toString() != "") {
            return
        }

        v.text = "X"
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j]?.let { cells.add(it) }
            }
        }
        var index = findBestMove(cells)
        cells[index].text = "O"
        roundCount++
        checkForWin()
        if (roundCount == 9) {
            draw()
        }
    }

    private fun checkForWin() {
        val field = Array(3) { arrayOfNulls<String>(3) }
        for (i in 0..2) {
            for (j in 0..2) {
                field[i][j] = buttons[i][j]!!.text.toString()
            }
        }
        for (i in 0..2) {
            if (field[i][0] == field[i][1] && field[i][0] == field[i][2] && field[i][0] != "") {
                if (field[i][0] == "X")
                    playerWins()
                else if (field[i][0] == "O")
                    computerWins()
            }
        }
        for (i in 0..2) {
            if (field[0][i] == field[1][i] && field[0][i] == field[2][i] && field[0][i] != "") {
                if (field[0][i] == "X")
                    playerWins()
                else if (field[0][i] == "O")
                    computerWins()
            }
        }
        if (field[0][0] == field[1][1] && field[0][0] == field[2][2] && field[0][0] != "") {
            if (field[0][0] == "X")
                playerWins()
            else if (field[0][0] == "O")
                computerWins()
        }
        if (field[0][2] == field[1][1] && field[0][2] == field[2][0] && field[0][2] != "") {
            if (field[0][2] == "X")
                playerWins()
            else if (field[0][2] == "O")
                computerWins()
        }
    }

    private fun playerWins() {
        playerPoints++
        Toast.makeText(this, "$player won!", Toast.LENGTH_SHORT).show()
        updatePointsText()
        resetBoard()
    }

    private fun computerWins() {
        computerPoints++
        Toast.makeText(this, "Computer won!", Toast.LENGTH_SHORT).show()
        updatePointsText()
        resetBoard()
    }

    private fun draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show()
        resetBoard()
    }

    private fun resetBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j]!!.text = ""
            }
        }
        roundCount = 0
    }

    private fun updatePointsText() {
        playerName!!.text = "$player(X):$playerPoints"
        computer!!.text = "Computer(O):$computerPoints"
    }

    private fun resetGame() {
        playerPoints = 0
        computerPoints = 0
        playerName.text = "$player(X):0"
        computer.text = "Computer(O):0"
        updatePointsText()
        resetBoard()
    }


    private fun findBestMove(board: List<Button>): Int {
        var bestVal = -1000
        var movePlace = 0
        for (i in board.indices) {
            if (board[i].text.isBlank()) {
                board[i].text = "O"
                var moveVal = minimax(board, 0, false)
                board[i].text = ""
                if (moveVal > bestVal) {
                    movePlace = i
                }
            }
        }
        return movePlace
    }

    private fun minimax(availableCells: List<Button>, depth: Int, isPlayer: Boolean): Int {

        var score = evaluationFunction()
        if (score == 10)
            return score;
        if (score == -10)
            return score;
        if (roundCount == 9) return 0

        if (isPlayer) {
            var best = -1000
            for (i in availableCells.indices) {
                if (availableCells[i].text.isBlank()) {
                    availableCells[i].text = "X"
                    best = Math.max(best, minimax(availableCells, depth + 1, !isPlayer))
                    availableCells[i].text = ""
                }
            }
            return best
        } else {
            var best = 1000
            for (i in availableCells.indices) {
                if (availableCells[i].text.isBlank()) {
                    availableCells[i].text = "O"
                    best = Math.min(best, minimax(availableCells, depth + 1, !isPlayer))
                    availableCells[i].text = ""
                }
            }
            return best
        }

    }

    private fun evaluationFunction(): Int {
        val field = Array(3) { arrayOfNulls<String>(3) }
        for (i in 0..2) {
            for (j in 0..2) {
                field[i][j] = buttons[i][j]!!.text.toString()
            }
        }
        for (i in 0..2) {
            if (field[i][0] == field[i][1] && field[i][0] == field[i][2] && field[i][0] != "") {
                if (field[i][0] == "X")
                    return 10
                else if (field[i][0] == "O")
                    return -10
            }
        }
        for (i in 0..2) {
            if (field[0][i] == field[1][i] && field[0][i] == field[2][i] && field[0][i] != "") {
                if (field[0][i] == "X")
                    return 10
                else if (field[0][i] == "O")
                    return -10
            }
        }
        if (field[0][0] == field[1][1] && field[0][0] == field[2][2] && field[0][0] != "") {
            if (field[0][0] == "X")
                return 10
            else if (field[0][0] == "O")
                return -10
        }
        if (field[0][2] == field[1][1] && field[0][2] == field[2][0] && field[0][2] != "") {
            if (field[0][2] == "X")
                return 10
            else if (field[0][2] == "O")
                return -10
        }

        return 0
    }
}