package com.example.kursovaya2024

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.HandlerThread
import android.widget.LinearLayout
import androidx.core.os.HandlerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kursovaya2024.Models.DateNameCardModel
import com.example.kursovaya2024.Utility.DateRecyclerAdapter
import com.example.kursovaya2024.Utility.ItemClickListener
import com.example.kursovaya2024.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import java.util.logging.Handler
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), ItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DateRecyclerAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var currentDate: LocalDateTime

    private lateinit var prefs: SharedPreferences
    private lateinit var prefsEditor: SharedPreferences.Editor

    var dateRecyclePage = 1
    var isLoading = false
    val limit = 7



    var DateList: ArrayList<DateNameCardModel> = ArrayList()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        //Инициализация  shared preferences
        InitPrefs()

        currentDate = LocalDateTime.now()
        binding.currentDateTV.text = getFormatedDate()
        binding.dateRecycler.layoutManager = layoutManager

        generateDate()

        //Добавление автоматической прогрузки списка
        binding.dateRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = adapter.itemCount

                if (!isLoading) {
                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        dateRecyclePage++
                        generateDate()
                    }
                }
            }
        })
        //добавление имени в хедер
        binding.headerMain.userNametagTV.text = getString(R.string.welcome) + " " + prefs.getString("NAME", "Incognito").toString()
        
    }

    @SuppressLint("SimpleDateFormat", "NotifyDataSetChanged")
    private fun generateDate() {
        isLoading = true

        val start = (dateRecyclePage - 1) * limit
        val end = (dateRecyclePage) * limit
        for (i in start..end) {
            DateList += DateNameCardModel(getFormatedDate())
            currentDate = currentDate.plusDays(1)
        }
        android.os.Handler().postDelayed({
            if (::adapter.isInitialized)
                adapter.notifyDataSetChanged()
            else {
                initializeAdapter()
            }
            isLoading = false
        }, 10)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getFormatedDate(): String {
        val dateParser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val date = dateParser.parse(currentDate.toString())
        val dateFormatter = SimpleDateFormat("MMMM dd", Locale("en"))
        return dateFormatter.format(date!!)
    }

    private fun initializeAdapter() {
        adapter = DateRecyclerAdapter(DateList, this)
        with(binding) {
            dateRecycler.adapter = adapter
        }
    }

    override fun onClick(position: Int) {
        binding.currentDateTV.text = DateList[position].getDateName()
    }

    private fun InitPrefs()
    {
        prefs = getSharedPreferences("SETTINGS", MODE_PRIVATE)
        prefsEditor = prefs.edit()
    }

}


