package com.example.formulariobien.ui.pantallaMain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.formulariobien.data.Repository
import com.example.formulariobien.databinding.ActivityMainBinding
import com.example.formulariobien.domain.modelo.Pelicula
import com.example.formulariobien.domain.usecases.peliculas.AddPeliculasUseCase
import com.example.formulariobien.domain.usecases.peliculas.DeletePeliculaUseCase
import com.example.formulariobien.domain.usecases.peliculas.GetPeliculaUseCase
import com.example.formulariobien.domain.usecases.peliculas.UpdatePeliculasUseCase
import java.time.LocalDate


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            AddPeliculasUseCase(),
            GetPeliculaUseCase(),
            DeletePeliculaUseCase(),
            UpdatePeliculasUseCase(),
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
                        retrocederButton.isEnabled = state.indiceActual > 0
                        avanzarButton.isEnabled =
                            state.indiceActual < Repository.getPelicula().size - 1
                        val peli = viewModel.uiState.value?.pelicula
                        editMovieText.setText(peli?.titulo)
                        editDirectorText.setText(peli?.director)
                        editDateText.setText(LocalDate.parse(peli?.fecha.toString()).toString())
                        editCastText.setText(peli?.cast)
                        recaudadoSeekBar.value = peli?.recaudado?: 0.0f
                        when (peli?.clasificacionEdad) {
                            Constantes.PARA_TODOS -> radioTodos.isChecked = true
                            Constantes.NO_7 -> radioNo7.isChecked = true
                            Constantes.NO_12 -> radioNo12.isChecked = true
                            Constantes.NO_16 -> radioNo16.isChecked = true
                            Constantes.NO_18 -> radioNo18.isChecked = true
                        }
                        checkBox.isChecked = peli?.generoComedia == true
                        checkBox2.isChecked = peli?.generoTragedia == true
                        checkBox3.isChecked = peli?.generoRomance == true
                        checkBox4.isChecked = peli?.generoTerror == true
                        estrellasRatingBar.rating = peli?.estrellas?: 0.0f
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

            deleteButton.setOnClickListener {
                viewModel.eliminarPelicula()
            }

            updateButton.setOnClickListener {
                val nuevaPelicula = Pelicula(
                    editMovieText.text.toString(),
                    editDirectorText.text.toString(),
                    LocalDate.parse(editDateText.text.toString()),
                    editCastText.text.toString(),
                    recaudadoSeekBar.value,
                    estrellasRatingBar.rating,
                    checkBox.isChecked,
                    checkBox4.isChecked,
                    checkBox3.isChecked,
                    checkBox2.isChecked,
                    when {
                        radioTodos.isChecked -> Constantes.PARA_TODOS
                        radioNo7.isChecked -> Constantes.NO_7
                        radioNo12.isChecked -> Constantes.NO_12
                        radioNo16.isChecked -> Constantes.NO_16
                        radioNo18.isChecked -> Constantes.NO_18
                        else -> ""
                    }
                )
                viewModel.actualizarPelicula(nuevaPelicula)
            }
        }
    }
}
