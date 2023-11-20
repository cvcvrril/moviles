package com.example.recyclerretrofitinesmr.ui.detailDirector

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerretrofitinesmr.databinding.ActivityDetailDirectorBinding
import com.example.recyclerretrofitinesmr.domain.Director
import com.example.recyclerretrofitinesmr.domain.Pelicula
import com.example.recyclerretrofitinesmr.ui.detailPelicula.DetailPeliculaActivity
import com.example.recyclerretrofitinesmr.ui.main.MainEvent
import com.example.recyclerretrofitinesmr.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.time.format.DateTimeFormatter



@AndroidEntryPoint
class DetailDirectorActivity : AppCompatActivity() {

    val context = this
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
                    viewModel.handleEvent(DetailDirectorEvent.DeletePelicula(pelicula))
                }

                override fun itemClicked(pelicula: Pelicula) {
                    val intent = Intent(context, DetailPeliculaActivity::class.java)
                    intent.putExtra(Constants.PELICULA, pelicula)
                    context.startActivity(intent)
                }
            })
        setupRecyclerView()

        val director: Director? = intent.getParcelableExtra(Constants.DIRECTOR)
        director?.let {
            observarViewModel(director)
        }
    }

    private fun observarViewModel(director: Director) {
        val birthDate = director.nacimiento.format(DateTimeFormatter.ofPattern(Constants.DATE_PATTERN))

        with(binding) {
            nombreCompletoDirector.setText(director.nombre)
            fechaDirector.setText(birthDate)
        }

        viewModel.handleEvent(DetailDirectorEvent.GetAllPeliculasIdDirector(director.id))

        viewModel.uiState.observe(this@DetailDirectorActivity) { state ->
            state.peliculas.let { peliculas ->
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
