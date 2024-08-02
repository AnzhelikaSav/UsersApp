package com.example.usersapp.presentation.common.validation

import android.util.Patterns

fun String.isValidName(): Boolean {
    return trim().split(' ').size == 2
}

fun String.isValidAge(): Boolean {
    return isNotEmpty() && toInt() in 0..125
}

fun String.isValidEmail(): Boolean {
    return isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}