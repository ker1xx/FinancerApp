package com.example.kursovaya2024.Utility.AuthenticationProperties

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kursovaya2024.EntryActivity
import com.example.kursovaya2024.Frames.EntryPhoneCodeFragment
import com.example.kursovaya2024.Frames.EntryPhoneNumberWriteFragment
import com.example.kursovaya2024.MainActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class PhoneAuthenticationProperties(activity: Activity){
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var storedVerificationId:String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private var context : Activity
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var prefs: SharedPreferences
    private lateinit var prefsEditor: SharedPreferences.Editor

    init{
        this.context = activity
    }

    fun SetupProperties() {
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                context.startActivity(Intent(context, MainActivity::class.java))
                Log.d("TAG", "onVerificationCompleted:$credential")
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(context, "Authorization Failed", Toast.LENGTH_LONG).show()
                Log.d("TAG",e.message.toString())
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {

                Log.d("TAG","onCodeSent:$verificationId")
                storedVerificationId = verificationId
                resendToken = token

                (context as EntryActivity).setFragment(EntryPhoneCodeFragment(storedVerificationId))
            }
        }
    }

    fun SendVerificationCode(number: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(context)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun CheckForAuthentificate() : Boolean{
        val currentUser = auth.currentUser
        return currentUser!=null
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")
                    val user = task.result?.user
                    prefs = context.getSharedPreferences("SETTINGS", AppCompatActivity.MODE_PRIVATE)
                    prefsEditor = prefs.edit()
                    if (user != null) {
                        prefsEditor.putString("PHONE_NUMBER",user.phoneNumber)
                        prefsEditor.apply()
                    }

                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(context as EntryActivity,"Error occurred while trying to confirm number\n Try again later please",Toast.LENGTH_LONG).show()
                        (context as EntryActivity).setFragment(EntryPhoneNumberWriteFragment())
                    }
                }
            }
    }
}