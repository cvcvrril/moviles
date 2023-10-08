package com.example.formulariobien.ui.pantallaMain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.formulariobien.databinding.ActivityMainBinding
import com.example.formulariobien.domain.modelo.Pelicula
import com.example.formulariobien.domain.usecases.peliculas.AddPeliculasUseCase
import com.example.formulariobien.domain.usecases.peliculas.GetPeliculaUseCase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            AddPeliculasUseCase(),
            GetPeliculaUseCase()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        Log.d("MainActivity", "ViewModel: $viewModel")
        viewModel.getPelicula(0)
        eventos()
        observarViewModel()
    }

    private fun observarViewModel() {
        viewModel.uiState.observe(this@MainActivity) { state ->
            state?.let {
                state.error?.let { error ->
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_LONG).show()
                    viewModel.errorMostrado()
                }
                binding.editMovieText.setText(state.pelicula.titulo)

            }
        }
    }

    private fun eventos() {
        with(binding) {
            addButton.setOnClickListener {
                viewModel.addPelicula(Pelicula(editMovieText.text.toString()))
            }
        }

    }

}