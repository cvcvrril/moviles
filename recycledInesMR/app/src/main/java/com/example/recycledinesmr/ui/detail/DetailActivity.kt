package com.example.recycledinesmr.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.recycledinesmr.R
import androidx.activity.viewModels
import com.example.recycledinesmr.data.Repository
import com.example.recycledinesmr.databinding.ActivityDetailBinding
import com.example.recycledinesmr.domain.modelo.Pelicula
import com.example.recycledinesmr.domain.usecases.AddPeliculasUseCase
import com.example.recycledinesmr.domain.usecases.DeletePeliculaUseCase
import com.example.recycledinesmr.domain.usecases.GetPeliculaUseCase
import com.example.recycledinesmr.domain.usecases.UpdatePeliculasUseCase
import com.example.recycledinesmr.ui.Constantes
import java.time.LocalDate

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var imageView: ImageView

    private val viewModel: MainViewModel by viewModels{
        MainViewModelFactory(
            AddPeliculasUseCase(),
            GetPeliculaUseCase(),
            DeletePeliculaUseCase(),
            UpdatePeliculasUseCase(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityDetailBinding.inflate(layoutInflater).apply {
            setContentView(R.layout.activity_detail)
        }
        observarViewModel()
        eventos()

    }

    private fun observarViewModel() {
        viewModel.uiState.observe(this@DetailActivity) { state ->
            state?.let {
                state.error?.let { error ->
                    Toast.makeText(this@DetailActivity, error, Toast.LENGTH_LONG).show()
                    viewModel.errorMostrado()
                }
                if (state.error == null) {
                    with(binding) {
                            state.indiceActual < Repository.getPelicula().size - 1
                        val peli = viewModel.uiState.value?.pelicula
                        editMovieText.setText(peli?.titulo)
                        editDirectorText.setText(peli?.director)
                        editDateText.setText(LocalDate.parse(peli?.fecha.toString()).toString())
                        when (peli?.clasificacionEdad) {
                            Constantes.PARA_TODOS -> radioTodos.isChecked = true
                            Constantes.NO_7 -> radioNo7.isChecked = true
                            Constantes.NO_12 -> radioNo12.isChecked = true
                            Constantes.NO_16 -> radioNo16.isChecked = true
                            Constantes.NO_18 -> radioNo18.isChecked = true
                        }
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

            deleteButton.setOnClickListener {
                viewModel.eliminarPelicula()
            }

            updateButton.setOnClickListener {
                val nuevaPelicula = Pelicula(
                    editMovieText.text.toString(),
                    editDirectorText.text.toString(),
                    LocalDate.parse(editDateText.text.toString()),
                    estrellasRatingBar.rating,
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