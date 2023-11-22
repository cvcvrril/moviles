package com.example.fragmentosinesmr.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.fragmentosinesmr.R
import com.example.fragmentosinesmr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        initNav()

        with(binding) {
            bottomAppBar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.fragment_primer -> {
                        navController.navigate(R.id.fragment_primer)
                        true
                    }

                    R.id.fragment_segundo -> {
                        // Handle rotation icon press
                        true
                    }

                    R.id.fragment_tercero -> {
                        // Handle dashboard icon press
                        true
                    }

                    else -> false
                }
            }

        }
    }

    private fun initNav(){
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHost.navController
        binding.bottomAppBar.setupWithNavController(navController)
    }

}