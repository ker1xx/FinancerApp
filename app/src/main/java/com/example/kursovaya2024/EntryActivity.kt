package com.example.kursovaya2024

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.kursovaya2024.Frames.EntryFirstFragment
import com.example.kursovaya2024.Frames.EntryPhoneNumberWriteFragment
import com.example.kursovaya2024.databinding.ActivityEntryBinding
import java.security.KeyStore.Entry

class EntryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEntryBinding
    lateinit var instance : EntryActivity



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ActivityEntryBinding.inflate(layoutInflater)

        supportFragmentManager.beginTransaction()
            .replace(binding.entryFrameLayout.id, EntryFirstFragment(), null)
            .commit()

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    fun setFragment(fragment: Fragment)
    {
        supportFragmentManager.beginTransaction()
            .replace(binding.entryFrameLayout.id, fragment, null)
            .addToBackStack("fragment_tag")
            .commit()
    }
}


