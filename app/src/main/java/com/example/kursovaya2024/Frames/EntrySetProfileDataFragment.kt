package com.example.kursovaya2024.Frames

import android.app.Activity.MODE_PRIVATE
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.kursovaya2024.MainActivity
import com.example.kursovaya2024.R
import com.example.kursovaya2024.Utility.BindingFragment
import com.example.kursovaya2024.databinding.FragmentEntrySetProfileDataBinding
import com.google.android.gms.common.util.IOUtils
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class EntrySetProfileDataFragment() : BindingFragment<FragmentEntrySetProfileDataBinding>() {

    private lateinit var prefs: SharedPreferences
    private lateinit var prefsEditor: SharedPreferences.Editor

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.confirmNameButton.setOnClickListener{
            if (binding.inputNameET.text.isNotEmpty())
            {
                prefs = requireActivity().getSharedPreferences("SETTINGS", MODE_PRIVATE)

                prefsEditor = prefs.edit()!!
                prefsEditor.putString("NAME",binding.inputNameET.text.toString())
                prefsEditor.putBoolean("IS_LOGINNED",true)
                prefsEditor.apply()

                requireActivity().startActivity(Intent(requireActivity(),MainActivity::class.java))
            }
        }
    }

    override fun getViewBinding() = FragmentEntrySetProfileDataBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getViewBinding()
        return binding.root
    }

}