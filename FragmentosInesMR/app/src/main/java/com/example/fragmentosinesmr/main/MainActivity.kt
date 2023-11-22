package com.example.fragmentosinesmr.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.example.fragmentosinesmr.R
import com.example.fragmentosinesmr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        with(binding){
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

}