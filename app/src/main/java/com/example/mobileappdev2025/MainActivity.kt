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
import androidx.core.view.isGone
import androidx.core.view.isVisible
import java.util.Random

class MainActivity : AppCompatActivity() {
    private var score :Int = 0;
    private var correctAnswer: String = "";
    private var submittedAnswer: String = "";
    private var correctImage: Int = 0;
    private val monsters = mapOf(
        R.drawable.far_s to "Farfetch'd",
        R.drawable.iron_s to "Iron Boulder",
        R.drawable.jumpluff_s to "Jumpluff",
        R.drawable.roselia_s to "Roselia")


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

        setScore(0)
        pickRandomPicture()
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
        val imageView = findViewById<ImageView>(R.id.you_won_image)
        val nextButton = findViewById<Button>(R.id.nextButton)
        val submitButton = findViewById<Button>(R.id.submission_button)
        if (correctAnswer == submittedAnswer){
            imageView.setImageResource(correctImage)
            setScore(score+1)
        }else{
            imageView.setImageResource(correctImage)
            setScore(score-1)
        }

        submitButton.visibility = View.GONE
        nextButton.visibility = View.VISIBLE

        nextButton.setOnClickListener{
            pickRandomPicture()
            nextButton.visibility = View.GONE
            submitButton.visibility = View.VISIBLE
        }

    }

    fun pickRandomPicture()
    {
        val shuffledMonsters = monsters.toList().shuffled().toMap()

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
