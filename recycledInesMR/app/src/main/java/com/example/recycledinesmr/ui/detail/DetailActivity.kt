package com.example.recycledinesmr.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import coil.load
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
    private lateinit var imageViewPoster: ImageView

    private val viewModel: MainViewModel by viewModels{
        MainViewModelFactory(
            AddPeliculasUseCase(),
            GetPeliculaUseCase(),
            DeletePeliculaUseCase(),
            UpdatePeliculasUseCase(assets.open("data.json")),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imageViewPoster = binding.imageView
        intent.extras?.let {
            val pelicula = it.getParcelable<Pelicula>("pelicula")
            if (pelicula != null) {
                observarViewModel(pelicula)
                eventos()
            }
        }
    }

    private fun observarViewModel(pelicula:Pelicula?) {
        viewModel.uiState.observe(this@DetailActivity) { state ->
            state?.let {
                state.error?.let { error ->
                    Toast.makeText(this@DetailActivity, error, Toast.LENGTH_LONG).show()
                    viewModel.errorMostrado()
                }
                if (state.error == null) {
                    with(binding) {
                            state.indiceActual < Repository.getPelicula().size - 1
                        editMovieText.setText(pelicula?.titulo)
                        editDirectorText.setText(pelicula?.director)
                        editDateText.setText(LocalDate.parse(pelicula?.fecha.toString()).toString())
                        when (pelicula?.clasificacionEdad) {
                            Constantes.PARA_TODOS -> radioTodos.isChecked = true
                            Constantes.NO_7 -> radioNo7.isChecked = true
                            Constantes.NO_12 -> radioNo12.isChecked = true
                            Constantes.NO_16 -> radioNo16.isChecked = true
                            Constantes.NO_18 -> radioNo18.isChecked = true
                        }
                        estrellasRatingBar.rating = pelicula?.estrellas?: 0.0f
                        imageView.load(pelicula?.imagen)
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
                        else -> Constantes.VACIO
                    }
                )
                viewModel.actualizarPelicula(nuevaPelicula)
            }
        }
    }
}