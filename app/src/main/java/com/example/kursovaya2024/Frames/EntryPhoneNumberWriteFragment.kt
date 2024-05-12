package com.example.kursovaya2024.Frames

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.kursovaya2024.EntryActivity
import com.example.kursovaya2024.R
import com.example.kursovaya2024.Utility.AuthenticationProperties.PhoneAuthenticationProperties
import com.example.kursovaya2024.Utility.BindingFragment
import com.example.kursovaya2024.Utility.PhoneNumberTextWatcher
import com.example.kursovaya2024.databinding.FragmentEntryPhoneNumberWriteBinding
import java.util.regex.Pattern

class EntryPhoneNumberWriteFragment() : BindingFragment<FragmentEntryPhoneNumberWriteBinding>() {

    lateinit var phoneAuthenticationProperties: PhoneAuthenticationProperties


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        phoneAuthenticationProperties = PhoneAuthenticationProperties(requireActivity())
        phoneAuthenticationProperties.SetupProperties()

        binding.enterPhoneEt.addTextChangedListener(PhoneNumberTextWatcher(binding.enterPhoneEt))

        val pattern = Pattern.compile(/* regex = */ "\\+7\\d{10}").toRegex()

        binding.getConfirmationCodeButton.setOnClickListener{
            var number=binding.enterPhoneEt.text.toString().trim()
            if(pattern.matches(number))
                phoneAuthenticationProperties.SendVerificationCode(number)
            else
                Toast.makeText(this.context,"Enter mobile number correctly.\ne.g. +7 xxx xxx xx xx",Toast.LENGTH_SHORT).show()
        }
    }

    override fun getViewBinding(): FragmentEntryPhoneNumberWriteBinding = FragmentEntryPhoneNumberWriteBinding.inflate(layoutInflater)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return binding.root
    }
}