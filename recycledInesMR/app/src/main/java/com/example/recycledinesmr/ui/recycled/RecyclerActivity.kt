package com.example.recycledinesmr.ui.recycled

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

class RecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecycledBinding

    private val viewModel: RecycledViewModel by viewModels {
        RecycledViewModelFactory(
            GetListaUseCase()
        )
    }

    private fun click(titulo: String) {
        Snackbar.make(
            findViewById<RecyclerView>(R.id.rvPeliculas), " $titulo", Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycled)

        intent.extras?.let { }

        binding = ActivityRecycledBinding.inflate(layoutInflater).apply {

        }

        val listaPeliculas = viewModel.getListaPeliculas()
//        val listaPeliculas = Repository(assets.open("data.json")).getLista()
        Log.d("RecyclerActivity", "Cantidad de películas: ${listaPeliculas.size}")
//        Toast.makeText(this, "el título es ${listaPeliculas[0].titulo}", Toast.LENGTH_SHORT).show()

        val rvPeliculas = this.findViewById<RecyclerView>(R.id.rvPeliculas)

        val adapter = PeliculasAdapter(listaPeliculas, ::click)

        listaPeliculas.let {
            rvPeliculas.adapter = adapter
            rvPeliculas.layoutManager = GridLayoutManager(this@RecyclerActivity, 1)
        }
    }
}