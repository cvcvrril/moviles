package com.example.recyclerretrofitinesmr.ui.detailDirector

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerretrofitinesmr.databinding.ActivityDetailDirectorBinding
import com.example.recyclerretrofitinesmr.domain.Director
import dagger.hilt.android.AndroidEntryPoint
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class DetailDirectorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailDirectorBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDirectorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val director: Director? = intent.getParcelableExtra("director")
        director?.let {
            observarViewModel(director)
        }
    }

    private fun observarViewModel(director: Director) {
        val birthDate = director.nacimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

        with(binding){
            nombreCompletoDirector.setText(director.nombre)
            fechaDirector.setText(birthDate)
        }


    }

}