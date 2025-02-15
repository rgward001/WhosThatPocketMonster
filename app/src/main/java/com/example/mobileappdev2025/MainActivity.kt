package com.example.mobileappdev2025

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Random

class MainActivity : AppCompatActivity() {
    private var score :Int = 0;
    private var correctAnswer: String = "";
    private var submittedAnswer: String = "";
    private var correctImage: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // above init layout ui

        pickRandomPicture()
        setScore(0)
    }

    fun radioButtonOnClick(view: View) {
        val radioButtons = listOf(
            R.id.iron_s,
            R.id.roselia_s,
            R.id.jumpluff_s,
            R.id.far_s
        )

        for (id in radioButtons) {
            findViewById<RadioButton>(id).isChecked = false
        }

        val selectedRadioButton = findViewById<RadioButton>(view.id)
        selectedRadioButton.isChecked = true

        submittedAnswer = when (view.id) {
            R.id.iron_s -> "Iron Boulder"
            R.id.roselia_s -> "Roselia"
            R.id.jumpluff_s -> "Jumpluff"
            R.id.far_s -> "Farfetch'd"
            else -> ""
        }
    }


    fun submissionButtonOnClick(view: View)
    {
        var imageView = findViewById<ImageView>(R.id.you_won_image)
        if (correctAnswer == submittedAnswer){
            imageView.setImageResource(correctImage)
            setScore(score+1)
        }else{
            setScore(score-1)
        }
    }

    fun pickRandomPicture()
    {
        var monsters = mapOf(
            R.drawable.far_s to "Farfetch'd",
            R.drawable.iron_s to "Iron Boulder",
            R.drawable.jumpluff_s to "Jumpluff",
            R.drawable.roselia_s to "Roselia")

        var shuffledMonsters = monsters.toList().shuffled().toMap()

        var mysteryMonster = findViewById<ImageView>(R.id.you_won_image)

        for (shuffledMonster in shuffledMonsters) {
            mysteryMonster.setImageResource(shuffledMonster.key)
            mysteryMonster.tag=shuffledMonster.key
            correctAnswer = shuffledMonster.value

            val correctImageResource = when (shuffledMonster.key) {
                R.drawable.far_s -> R.drawable.far
                R.drawable.iron_s -> R.drawable.iron
                R.drawable.jumpluff_s -> R.drawable.jumpluff
                R.drawable.roselia_s -> R.drawable.roselia
                else -> shuffledMonster.key
            }
            correctImage = correctImageResource
        }
    }

    fun setScore(_score: Int)
    {
        score = _score;

        // vari = (condition) ? true : false;

        findViewById<TextView>(R.id.score_text).text = "Score: $score"
    }
}
