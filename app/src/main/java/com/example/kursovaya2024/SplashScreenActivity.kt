package com.example.kursovaya2024

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var newActivityIntent: Intent
    private lateinit var prefs: SharedPreferences
    private lateinit var prefsEditor: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        prefs = getSharedPreferences("SETTINGS", MODE_PRIVATE)


        Handler().postDelayed({
            if (!prefs.contains("IS_LOGINNED"))
            {
                prefsEditor = prefs.edit()
                prefsEditor.putBoolean("IS_LOGINNED",false)
                prefsEditor.apply()
                newActivityIntent = Intent(this, EntryActivity::class.java)
            }
            else
            {
                val isloginned = prefs.getBoolean("IS_LOGINNED",false)
                if (!isloginned)
                    newActivityIntent = Intent(this, EntryActivity::class.java)
                else
                    newActivityIntent = Intent(this,MainActivity::class.java)
            }
            startActivity(newActivityIntent)
            finish()
        }, 5500)



    }
}