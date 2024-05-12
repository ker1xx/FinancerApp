package com.example.kursovaya2024.Frames

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.kursovaya2024.EntryActivity
import com.example.kursovaya2024.MainActivity
import com.example.kursovaya2024.R
import com.example.kursovaya2024.Utility.BindingFragment
import com.example.kursovaya2024.databinding.FragmentEntryPhoneCodeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class EntryPhoneCodeFragment(storedVerificationId: String) :
    BindingFragment<FragmentEntryPhoneCodeBinding>() {

    lateinit var auth: FirebaseAuth
    private var storedVerificationId : String

    init{
        this.storedVerificationId = storedVerificationId
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val storedVerificationId = storedVerificationId
        auth=FirebaseAuth.getInstance()

        binding.idOtp.addTextChangedListener (object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (binding.idOtp.text.toString().count() == 6)
                    {
                        val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                            storedVerificationId.toString(), binding.idOtp.text.toString().trim()
                        )
                        signInWithPhoneAuthCredential(credential)
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })
        }

    override fun getViewBinding() = FragmentEntryPhoneCodeBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = getViewBinding()
        return binding.root
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener((activity as EntryActivity)) { task ->
                if (task.isSuccessful) {
                    (requireActivity() as EntryActivity).setFragment(EntrySetProfileDataFragment())
// ...
                } else {
// Sign in failed, display a message and update the UI
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
// The verification code entered was invalid
                        Toast.makeText((activity as EntryActivity),"Invalid One Time Password",Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

}