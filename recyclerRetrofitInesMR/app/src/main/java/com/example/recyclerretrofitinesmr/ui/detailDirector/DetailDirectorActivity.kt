package com.example.recyclerretrofitinesmr.ui.detailDirector

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerretrofitinesmr.databinding.ActivityDetailDirectorBinding
import com.example.recyclerretrofitinesmr.domain.Director
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
        peliculaAdapter = PeliculaAdapter()
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

        viewModel.handleEvent(DetailDirectorEvent.GetAllPeliculasIdDirector(director.id.toString()))

        viewModel.uiState.observe(this@DetailDirectorActivity) { state ->
            state.peliculas.let { peliculas ->
                Timber.d("DetailDirectorActivity(Lista)", "Lista de películas: $peliculas")
                peliculaAdapter.submitList(peliculas)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this@DetailDirectorActivity)
        binding.recyclerView.adapter = peliculaAdapter
    }
}
