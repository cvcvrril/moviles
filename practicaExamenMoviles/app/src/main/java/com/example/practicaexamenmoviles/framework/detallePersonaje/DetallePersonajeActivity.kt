package com.example.practicaexamenmoviles.framework.detallePersonaje

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.practicaexamenmoviles.R
import com.example.practicaexamenmoviles.databinding.ActivityDetallePersonajeBinding
import com.example.practicaexamenmoviles.framework.personaje.PersonajeEvent
import com.example.practicaexamenmoviles.framework.personaje.PersonajeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetallePersonajeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetallePersonajeBinding
    private lateinit var navController: NavController
    private val viewModel: DetallePersonajeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallePersonajeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        if (intent.hasExtra("id")) {
            val id = intent.getIntExtra("id", 1)
            viewModel.handleEvent(DetallePersonajeEvent.GetPersonaje(id))
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