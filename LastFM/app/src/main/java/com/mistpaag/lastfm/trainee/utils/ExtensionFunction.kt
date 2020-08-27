package com.mistpaag.lastfm.trainee.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.IdRes
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import java.lang.ref.WeakReference


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

fun NavigationView.setupWithUniqueFragment(navController: NavController) {

    this.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            val parent = this@setupWithUniqueFragment.parent
            if (item.itemId == navController.currentDestination?.id) {
                if (parent is DrawerLayout) {
                    parent.closeDrawer(this@setupWithUniqueFragment, true)
                }
                return true
            }
            val handled = NavigationUI.onNavDestinationSelected(item, navController)
            if (handled) {
                if (parent is DrawerLayout) {
                    parent.closeDrawer(this@setupWithUniqueFragment, true)
                }
            }
            return handled
        }

    })

    val weakReference = WeakReference<NavigationView>(this@setupWithUniqueFragment)
    navController.addOnDestinationChangedListener(
        object : NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination, arguments: Bundle?
            ) {
                val view = weakReference.get()
                if (view == null) {
                    navController.removeOnDestinationChangedListener(this)
                    return
                }
                val menu = view.menu
                var h = 0
                val size = menu.size()
                while (h < size) {
                    val item = menu.getItem(h)
                    item.isChecked =
                        matchDestination(
                            destination,
                            item.itemId
                        )
                    h++
                }
            }
        })
}

internal fun matchDestination(
    destination: NavDestination,
    @IdRes destId: Int
): Boolean {
    var currentDestination: NavDestination? = destination
    while (currentDestination!!.id != destId && currentDestination.parent != null) {
        currentDestination = currentDestination.parent
    }
    return currentDestination.id == destId
}
