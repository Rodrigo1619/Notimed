package com.mrroboto.notimed.views

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideKeyboard() {
    val keyboard = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    keyboard.hideSoftInputFromWindow(windowToken, 0)
}