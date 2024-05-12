package com.example.kursovaya2024.Utility

import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.animation.Animation
import android.widget.EditText
import android.widget.TextView


class MyFocusChangeListener(private val editText: EditText, private val animation: Animation, private val hint: TextView) :
    OnFocusChangeListener {
    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (hasFocus && !editText.text.toString().isEmpty()) {
            hint.startAnimation(animation)
            hint.visibility = View.VISIBLE
        } else {
            animation.cancel()
            hint.visibility = View.INVISIBLE
            hint.clearAnimation()
        }
    }
}