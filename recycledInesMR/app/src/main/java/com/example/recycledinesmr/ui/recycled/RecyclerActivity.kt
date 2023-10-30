package com.example.recycledinesmr.ui.recycled

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recycledinesmr.data.Repository
import com.example.recycledinesmr.databinding.ActivityRecycledBinding
import com.example.recycledinesmr.domain.usecases.GetListaUseCase
import com.example.recycledinesmr.ui.PeliculasAdapter
import com.example.recycledinesmr.ui.detail.DetailActivity

class RecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecycledBinding
    private lateinit var adapter: PeliculasAdapter

    private val viewModel: RecycledViewModel by viewModels {
        RecycledViewModelFactory(
            GetListaUseCase(Repository(assets.open("data.json")))
        )
    }

    private fun click(titulo: String) {
        val listaPeliculas = viewModel.getListaPeliculas()
        val pelicula = listaPeliculas.find { it.titulo == titulo }
        Log.d("RecyclerActivity", "Pelicula: $pelicula")
        val intent = Intent(this@RecyclerActivity, DetailActivity::class.java)
        intent.putExtra("pelicula", pelicula)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecycledBinding.inflate(layoutInflater).apply {
            setContentView(root)
            adapter = PeliculasAdapter(listOf(), ::click)
            rvPeliculas.adapter = adapter
            rvPeliculas.layoutManager =
                GridLayoutManager(this@RecyclerActivity, 1)
        }
        Log.d("LOG_BUNDLE", "El valor del bundle: ${intent} ")
        observarViewModel()

    }

    private fun observarViewModel() {
        viewModel.uiState.observe(this@RecyclerActivity) { state ->
            state?.let {
                state.error?.let { error ->
                    Toast.makeText(this@RecyclerActivity, error, Toast.LENGTH_SHORT).show()
                    viewModel.errorMostrado()
                }
                if (state.error == null) {
//                    val listaPeliculas = viewModel.getListaPeliculas()
                    viewModel.uiState.value?.let { adapter.cambiarLista(it.lista) }
                }
            }

        }
    }
}