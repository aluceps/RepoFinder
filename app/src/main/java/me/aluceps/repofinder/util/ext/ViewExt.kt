package me.aluceps.repofinder.util.ext

import android.view.View

fun View.toVisible() {
    visibility = View.VISIBLE
}

fun View.toGone() {
    visibility = View.GONE
}

fun View.isVisible(): Boolean = visibility == View.VISIBLE

fun View.isGone(): Boolean = visibility == View.GONE