package com.example.formulariobien.ui.pantallaMain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.formulariobien.databinding.ActivityMainBinding
import com.example.formulariobien.domain.modelo.Pelicula
import com.example.formulariobien.domain.usecases.peliculas.AddPeliculasUseCase
import com.example.formulariobien.domain.usecases.peliculas.GetPeliculaUseCase
import java.time.LocalDate

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
        observarViewModel()
        eventos()
    }

    private fun observarViewModel() {
        viewModel.uiState.observe(this@MainActivity) { state ->
            state?.let {
                state.error?.let { error ->
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_LONG).show()
                    viewModel.errorMostrado()
                }
                if (state.error == null) {
                    with(binding) {
                        val peli = viewModel.uiState.value?.pelicula
                        editMovieText.setText(peli?.titulo)
                        editDirectorText.setText(peli?.director)
                        editDateText.setText(LocalDate.parse(peli?.fecha.toString()).toString())
                        editCastText.setText(peli?.cast)
                        estrellasRatingBar.rating = peli?.estrellas?.toFloat() ?: 0.0f
                        checkBox.isChecked = peli?.generoComedia == true
                        checkBox2.isChecked = peli?.generoTragedia == true
                        checkBox3.isChecked = peli?.generoRomance == true
                        checkBox4.isChecked = peli?.generoTerror == true
                        
                    }
                }

            }
        }
    }

    private fun eventos() {
        with(binding) {
            addButton.setOnClickListener {
                viewModel.addPelicula(
                    Pelicula(
                        editMovieText.text.toString(),
                        editDirectorText.text.toString(),
                        LocalDate.parse(editDateText.text.toString())
                    )
                )
            }
            avanzarButton.setOnClickListener {
                viewModel.avanzarPelicula()
            }

            retrocederButton.setOnClickListener {
                viewModel.retrocederPelicula()
            }
        }
    }
}