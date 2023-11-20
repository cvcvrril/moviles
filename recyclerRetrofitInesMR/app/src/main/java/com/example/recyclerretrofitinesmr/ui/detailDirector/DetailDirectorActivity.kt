package com.example.recyclerretrofitinesmr.ui.detailDirector

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerretrofitinesmr.databinding.ActivityDetailDirectorBinding
import com.example.recyclerretrofitinesmr.domain.Director
import com.example.recyclerretrofitinesmr.domain.Pelicula
import com.example.recyclerretrofitinesmr.ui.main.MainEvent
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class DetailDirectorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailDirectorBinding
    private val viewModel: DetailDirectorViewModel by viewModels()
    private lateinit var peliculaAdapter: PeliculaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDirectorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        peliculaAdapter = PeliculaAdapter(this@DetailDirectorActivity,
            object : PeliculaAdapter.PeliculaActions{
                override fun onDelete(pelicula: Pelicula) {
                    Timber.tag("PeliculaAdapter").d("Peloicula eliminada: %s", pelicula.id)
                    viewModel.handleEvent(DetailDirectorEvent.DeletePelicula(pelicula))
                    Timber.tag("Peliculas (MainActivity1)")
                        .d("Peliculas: " + viewModel.handleEvent(DetailDirectorEvent.DeletePelicula(pelicula)))
                }
            })
        setupRecyclerView()

        val director: Director? = intent.getParcelableExtra("director")
        director?.let {
            observarViewModel(director)
        }
    }

    private fun observarViewModel(director: Director) {
        val birthDate = director.nacimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

        with(binding) {
            nombreCompletoDirector.setText(director.nombre)
            fechaDirector.setText(birthDate)
        }

        viewModel.handleEvent(DetailDirectorEvent.GetAllPeliculasIdDirector(director.id))

        viewModel.uiState.observe(this@DetailDirectorActivity) { state ->
            state.peliculas.let { peliculas ->
                Timber.d("DetailDirectorActivity(Lista)", "Lista de películas: $peliculas")
                peliculaAdapter.submitList(peliculas)
            }
        }

        val touchHelper = ItemTouchHelper(peliculaAdapter.swipeGesture)
        touchHelper.attachToRecyclerView(binding.rvPeliculas)
    }

    private fun setupRecyclerView() {
        binding.rvPeliculas.layoutManager = LinearLayoutManager(this@DetailDirectorActivity)
        binding.rvPeliculas.adapter = peliculaAdapter
    }
}
