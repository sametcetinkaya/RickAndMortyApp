package com.sametcetinkaya.rickandmortyapp.utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.sametcetinkaya.rickandmortyapp.R

fun getColorStatus(gender: String, context: Context): Int {
    return when (gender) {
        "Male" -> {
            ContextCompat.getColor(context, R.color.male)
        }
        "Female" -> {
            ContextCompat.getColor(context, R.color.female)
        }
        "unknown" -> {
            ContextCompat.getColor(context, R.color.unknown)
        }
        else -> {
            ContextCompat.getColor(context, R.color.unknown)
        }
    }
}