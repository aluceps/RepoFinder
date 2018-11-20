package me.aluceps.repofinder.util.ext

import androidx.databinding.BindingAdapter
import android.widget.ImageView
import me.aluceps.repofinder.util.common.GlideApp

@BindingAdapter("setAvater")
fun ImageView.setAvater(url: String) {
    GlideApp.with(this)
            .load(url)
            .fitCenter()
            .into(this)
}