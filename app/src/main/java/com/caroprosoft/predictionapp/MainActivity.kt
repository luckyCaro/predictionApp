package com.caroprosoft.predictionapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
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
    lateinit var sharedPreferences: SharedPreferences
    var a = 0
    var b = 0

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
        a = (0..< resources.getTextArray(R.array.Predictions).size).random()
        b = (0..< resources.getTextArray(R.array.Recommendation).size).random()
        tv_prediction = findViewById(R.id.tv_dailyPrediction)
        tv_recommendation = findViewById(R.id.tv_dailyRecommendation)
        sharedPreferences = getSharedPreferences("mem", Context.MODE_PRIVATE)
        checkDate()
        fillRecommendation()
    }

    fun checkDate() {
        val currentDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        if (!sharedPreferences.contains(currentDate)) {
            sharedPreferences.edit().apply {
                putInt(currentDate, a)
                apply()
            }
            fillPrediction()
        } else {
            a = sharedPreferences.getInt(currentDate, 0)
            fillPrediction()
        }
    }

    fun fillPrediction() {
        tv_prediction.text = "${resources.getTextArray(R.array.Predictions)[a]}"
    }

    fun fillRecommendation() {
        tv_recommendation.text = "${resources.getTextArray(R.array.Recommendation)[b]}"
    }
}