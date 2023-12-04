package com.example.practicaexamenmoviles.framework.personaje

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.practicaexamenmoviles.R
import com.example.practicaexamenmoviles.databinding.ActivityDetallePersonajeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetallePersonajeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetallePersonajeBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallePersonajeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        if (intent.hasExtra("id")) {
            val id = intent.getIntExtra("id", 1)
        }

        with(binding){

        }
        
        initUI()
    }

    private fun initUI() {
        initNavigation()
    }

    private fun initNavigation() {
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHost.navController
        binding.bottomNavView.setupWithNavController(navController)
    }

}