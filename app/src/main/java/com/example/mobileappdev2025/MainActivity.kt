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

        val gridLayout = findViewById<GridLayout>(R.id.gridRadioGroup)
        val radioButtons = mutableListOf<RadioButton>()

        for (i in 0 until gridLayout.childCount){
            val child = gridLayout.getChildAt(i)
            if (child is RadioButton){
                radioButtons.add(child)
            }
        }

        for (radio in radioButtons){
            radio.setOnClickListener{
                for (r in radioButtons){
                    if (r!=radio) r.isChecked = false
                }
            }
        }

        pickRandomPicture()
        setScore(0)
    }

    fun radioButtonOnClick(view: View)
    {
        if (view.id == R.id.radioButton){
            findViewById<TextView>(R.id.score_text).text = "R.id.radioButton"
        }

        if (view.id == R.id.radioButton2){
            findViewById<TextView>(R.id.score_text).text = "R.id.radioButton2"
        }

        if (view.id == R.id.radioButton3){
            findViewById<TextView>(R.id.score_text).text = "R.id.radioButton3"
        }
        if (view.id == R.id.radioButton4){
            findViewById<TextView>(R.id.score_text).text = "R.id.radioButton3"
        }
    }

    fun submissionButtonOnClick(view: View)
    {
        Log.d("mad", "Submit")

        pickRandomPicture()
    }

    fun pickRandomPicture()
    {
        var monsters = arrayOf(R.drawable.far_s, R.drawable.iron_s, R.drawable.jumpluff_s, R.drawable.roselia_s)
        var mysteryMonster = findViewById<ImageView>(R.id.you_won_image)

        var rand = Random()

        var num = rand.nextInt(4)
        mysteryMonster.setImageResource(monsters[num])
    }

    fun setScore(_score: Int)
    {
        score = _score;

        // vari = (condition) ? true : false;

        findViewById<TextView>(R.id.score_text).text = "Score: $score"
    }
}
