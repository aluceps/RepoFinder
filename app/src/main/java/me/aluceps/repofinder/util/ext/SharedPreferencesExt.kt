package me.aluceps.repofinder.util.ext

import android.content.SharedPreferences
import androidx.core.content.edit

fun SharedPreferences.getOAuth(): String? = getString("my_oauth", "")

fun SharedPreferences.setOAuth(value: String) {
    edit(commit = true) {
        putString("my_oauth", value)
    }
}