package com.example.recyclerretrofitinesmr.ui.detailPelicula

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerretrofitinesmr.databinding.ActivityDetailDirectorBinding
import com.example.recyclerretrofitinesmr.databinding.ActivityDetailPeliculaBinding
import com.example.recyclerretrofitinesmr.domain.Director
import com.example.recyclerretrofitinesmr.domain.Pelicula
import com.example.recyclerretrofitinesmr.ui.detailDirector.DetailDirectorViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class DetailPeliculaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPeliculaBinding
    private val viewModel: DetailPeliculaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pelicula: Pelicula? = intent.getParcelableExtra("pelicula")

        pelicula?.let {
            observarViewModel(pelicula)
            viewModel.loadPeliculaDetails(pelicula)
        }
    }

    private fun observarViewModel(pelicula: Pelicula) {
        val releaseDate = pelicula.fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

        with(binding) {
            nombreCompletoDirector.setText(pelicula.titulo)
            fechaDirector.setText(releaseDate)
        }
    }

}