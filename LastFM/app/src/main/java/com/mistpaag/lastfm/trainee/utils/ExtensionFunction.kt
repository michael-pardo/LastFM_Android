package com.mistpaag.lastfm.trainee.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText


fun ViewGroup.inflate(layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

fun Int.isSucessfull() = this == 200

fun EditText.getTextString() = this.text.toString()


