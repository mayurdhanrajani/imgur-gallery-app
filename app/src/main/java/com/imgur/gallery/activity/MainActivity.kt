package com.imgur.gallery.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.imgur.gallery.R
import com.imgur.gallery.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/** This activity is used to navigate between screens using fragments **/
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /** This variable will store the instance of MainActivity layout file views **/
    private lateinit var binding: ActivityMainBinding

    /** This function will store the instance of NavHostFragment **/
    private lateinit var navHostFragment: NavHostFragment

    /** This function will store the instance of NavController **/
    private lateinit var navController: NavController

    /** This function is used to initialize the process of the screen **/
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeViews()
    }

    /** This function will start calling the functions to initialize views **/
    private fun initializeViews() {
        setNavController()
        setActionBar()
    }

    /** This function will initialize the navController and navHost **/
    private fun setNavController() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    /** This function will setup the toolbar with the navController **/
    private fun setActionBar() {
        setSupportActionBar(binding.toolbar)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.imageFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

}