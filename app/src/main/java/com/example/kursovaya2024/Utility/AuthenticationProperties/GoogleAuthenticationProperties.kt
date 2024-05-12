package com.example.kursovaya2024.Utility.AuthenticationProperties

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat.getString
import androidx.core.content.ContextCompat.startActivities
import com.example.kursovaya2024.EntryActivity
import com.example.kursovaya2024.Frames.EntryFirstFragment
import com.example.kursovaya2024.Frames.EntrySetProfileDataFragment
import com.example.kursovaya2024.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.Firebase

class GoogleAuthenticationProperties(private var context: Context) {
    lateinit var auth: FirebaseAuth

    private lateinit var googleSignInClient : GoogleSignInClient

    fun getInstance()
    {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)

                    // Your server's client ID, not your Android client ID.
                    .requestIdToken("360585903176-qf0v87h2mqdgjk834aib9km37d4bsn4q.apps.googleusercontent.com")
                    .requestEmail()
                    // Only show accounts previously used to sign in.
                    .build()

        googleSignInClient = GoogleSignIn.getClient(context, gso)

        auth = Firebase.auth

        var currentUser = auth.currentUser
    }


    fun signInGoogle(launcher : ActivityResultLauncher<Intent>){
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

}