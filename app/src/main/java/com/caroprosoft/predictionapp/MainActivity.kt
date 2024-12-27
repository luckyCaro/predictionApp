package com.caroprosoft.predictionapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var tv_prediction: TextView
    lateinit var tv_recommendation: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tv_prediction = findViewById(R.id.tv_dailyPrediction)
        tv_recommendation = findViewById(R.id.tv_dailyRecommendation)
        fillPrediction()
        fillRecommendation()
        val currentDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date()) //debug
        val toast = Toast.makeText(this, "$currentDate", Toast.LENGTH_LONG).show() //debug
    }

    fun fillPrediction() {
        var a = (0..< resources.getTextArray(R.array.Predictions).size).random()
        tv_prediction.text = "${resources.getTextArray(R.array.Predictions)[a]}"
    }

    fun fillRecommendation() {
        var a = (0..< resources.getTextArray(R.array.Recommendation).size).random()
        tv_recommendation.text = "${resources.getTextArray(R.array.Recommendation)[a]}"
    }
}