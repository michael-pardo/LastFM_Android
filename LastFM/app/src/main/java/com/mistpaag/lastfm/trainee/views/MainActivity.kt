package com.mistpaag.lastfm.trainee.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.SearchView
import androidx.annotation.IdRes
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.mistpaag.lastfm.trainee.R
import com.mistpaag.lastfm.trainee.databinding.ActivityMainBinding
import com.mistpaag.lastfm.trainee.utils.setupWithUniqueFragment
import com.mistpaag.lastfm.trainee.views.main.SharedActivityViewModel
import org.koin.android.ext.android.inject
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding

    private val sharedViewModel by inject<SharedActivityViewModel> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController = findNavController(R.id.nav_host)
        binding.menuImg.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        NavigationUI.setupWithNavController(binding.navigationView, navController)
        binding.navigationView.setupWithUniqueFragment(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
             binding.titleToolbar.text = destination.label
        }


        binding.search.setOnSearchClickListener{
            binding.titleToolbar.isVisible = false
            binding.menuImg.isVisible = false

            sharedViewModel.isSearching(true)
        }


        binding.search.setOnCloseListener{
            binding.titleToolbar.isVisible = true
            binding.menuImg.isVisible = true
            sharedViewModel.isSearching(false)
            return@setOnCloseListener false
        }

        binding.search.setOnQueryTextListener(this)



    }

    override fun onBackPressed() {
        super.onBackPressed()
    }



    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        p0?.let {
            sharedViewModel.search(it)
        }
        return false
    }
}



