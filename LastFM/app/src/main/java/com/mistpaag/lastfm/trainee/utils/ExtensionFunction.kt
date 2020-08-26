package com.mistpaag.lastfm.trainee.utils

import android.content.Context
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView


fun ViewGroup.inflate(layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

fun Int.isSucessfull() = this == 200

fun EditText.getTextString() = this.text.toString()

private fun RecyclerView.getCurrentPosition() : Int {
    return (this.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
}

fun RecyclerView.smoothSnapToPosition(position: Int, snapMode: Int = LinearSmoothScroller.SNAP_TO_START) {
    val smoothScroller = object : LinearSmoothScroller(this.context) {
        override fun getVerticalSnapPreference(): Int = snapMode
        override fun getHorizontalSnapPreference(): Int = snapMode
    }
    smoothScroller.targetPosition = position
    layoutManager?.startSmoothScroll(smoothScroller)
}

fun Context.haveConnection():Boolean{
    var connectivityManager: ConnectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    var networkInfo = connectivityManager.activeNetworkInfo
    return (networkInfo!=null && networkInfo.isAvailable && networkInfo.isConnected)
}
