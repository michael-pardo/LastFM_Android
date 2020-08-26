package com.mistpaag.lastfm.trainee.utils

import android.content.Context
import android.util.DisplayMetrics

class ScreenUtil(val context:Context) {
    fun getPositionForScreenDensity(): Int {
        val metrics: DisplayMetrics = context.resources.displayMetrics
        return when (metrics.density) {
            in 0f..1f -> 0
            in 1f..1.5f -> 1
            in 1.5f..2f -> 2
            in 2f..3f -> 3
            else -> 4

        }
    }
}