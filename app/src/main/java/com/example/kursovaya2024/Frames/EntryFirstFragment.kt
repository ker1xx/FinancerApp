package com.example.kursovaya2024.Frames

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.kursovaya2024.EntryActivity
import com.example.kursovaya2024.Utility.AuthenticationProperties.GoogleAuthenticationProperties
import com.example.kursovaya2024.Utility.AuthenticationProperties.PhoneAuthenticationProperties
import com.example.kursovaya2024.Utility.BindingFragment
import com.example.kursovaya2024.databinding.FragmentEntryFirstBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider

class EntryFirstFragment() : BindingFragment<FragmentEntryFirstBinding>() {


    override fun getViewBinding() = FragmentEntryFirstBinding.inflate(layoutInflater)

    private lateinit var google : GoogleAuthenticationProperties
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.PhoneLoginButton.setOnClickListener {

            (activity as EntryActivity).setFragment(EntryPhoneNumberWriteFragment())
        }

        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

                handleResults(task)
            }
        }

        binding.GoogleLoginButton.setOnClickListener {
            google = GoogleAuthenticationProperties(requireContext())
            google.getInstance()
            google.signInGoogle(launcher)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return binding.root
    }

    fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account: GoogleSignInAccount? = task.result
            if(account != null){
                updateUI(account)
            }
        }else{
            Toast.makeText(context, "Error occurred while login!", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount){
        val creditial = GoogleAuthProvider.getCredential(account.idToken,null)
        google.auth.signInWithCredential(creditial).addOnCompleteListener{
            if (it.isSuccessful){
                (context as EntryActivity).setFragment(EntrySetProfileDataFragment())
            }
        }
    }

}