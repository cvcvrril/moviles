package com.example.recycledinesmr.ui.recycled

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.example.recycledinesmr.R
import com.example.recycledinesmr.data.Repository
import com.example.recycledinesmr.databinding.ActivityRecycledBinding
import com.example.recycledinesmr.domain.usecases.GetListaUseCase
import com.example.recycledinesmr.ui.PeliculasAdapter
import com.example.recycledinesmr.ui.detail.DetailActivity

class RecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecycledBinding

    private val viewModel: RecycledViewModel by viewModels {
        RecycledViewModelFactory(
            GetListaUseCase()
        )
    }

    private fun click(titulo: String) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        intent.extras?.let { }

        binding = ActivityRecycledBinding.inflate(layoutInflater).apply{
            setContentView(R.layout.activity_recycled)
        }

//        val listaPeliculas = viewModel.getListaPeliculas()
        val listaPeliculas = Repository(assets.open("data.json")).getLista()
        Log.d("RecyclerActivity", "Cantidad de películas: ${listaPeliculas.size}")
//        Toast.makeText(this, "el título es ${listaPeliculas[0].titulo}", Toast.LENGTH_SHORT).show()

        val rvPeliculas = this.findViewById<RecyclerView>(R.id.rvPeliculas)

        val adapter = PeliculasAdapter(listaPeliculas){
            titulo -> click(titulo)
             val pelicula = listaPeliculas.find { it.titulo == titulo }
            val intent = Intent(this@RecyclerActivity, DetailActivity::class.java)
            intent.putExtra("pelicula", pelicula)
            startActivity(intent)
            Log.d("LOG_BUNDLE", "El valor del intent: ${intent} ")
        }

        listaPeliculas.let {
            rvPeliculas.adapter = adapter
            rvPeliculas.layoutManager = GridLayoutManager(this@RecyclerActivity, 1)
        }
    }
}