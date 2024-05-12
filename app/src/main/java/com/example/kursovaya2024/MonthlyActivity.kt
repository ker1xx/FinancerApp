package com.example.kursovaya2024

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kursovaya2024.databinding.ActivityMonthlyBinding

class MonthlyActivity : AppCompatActivity() {

    lateinit var binding : ActivityMonthlyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMonthlyBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    private fun generateDays(){

    }
}