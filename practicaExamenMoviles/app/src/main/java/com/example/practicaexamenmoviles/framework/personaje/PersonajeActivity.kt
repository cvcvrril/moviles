package com.example.practicaexamenmoviles.framework.personaje

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.practicaexamenmoviles.R
import com.example.practicaexamenmoviles.databinding.ActivityPersonajeBinding
import com.example.practicaexamenmoviles.domain.model.Personaje
import com.example.practicaexamenmoviles.framework.detallePersonaje.DetallePersonajeActivity
import com.example.practicaexamenmoviles.framework.newPersonaje.NewPersonajeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PersonajeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonajeBinding
    private lateinit var personajeAdapter: PersonajeAdapter
    private val viewModel: PersonajeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonajeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        if (intent.hasExtra("id")) {
            val id = intent.getIntExtra("id", 1)
            viewModel.handleEvent(PersonajeEvent.GetVideojuego(id))
        }

        personajeAdapter = PersonajeAdapter(this,
            object : PersonajeAdapter.PersonajeActions{
                override fun onDelete(personaje: Personaje) {
                    viewModel.handleEvent(PersonajeEvent.DeletePersonaje(personaje.id))
                }

                override fun onClickItem(idPersonaje: Int) {
                    click(idPersonaje)
                }

            })

        with(binding) {
            rvPersonajes.adapter = personajeAdapter
            rvPersonajes.layoutManager = GridLayoutManager(this@PersonajeActivity, 1)
        }

        val touchHelper = ItemTouchHelper(personajeAdapter.swipeGesture)
        touchHelper.attachToRecyclerView(binding.rvPersonajes)

        val context = this
        lifecycleScope.launch {

        }
        observarViewModel()
        configAppBar();

    }

    private fun observarViewModel() {
        viewModel.uiState.observe(this@PersonajeActivity) { state ->
            state.error?.let { error ->
                Toast.makeText(this@PersonajeActivity, error, Toast.LENGTH_SHORT).show()
                viewModel.handleEvent(PersonajeEvent.ErrorVisto)
            }
            if (state.error == null) {
                pintarPersonaje(state)
            }
            if (state.personajes.isNotEmpty()) {
                personajeAdapter.submitList(state.personajes)
            }else{
                personajeAdapter.submitList(emptyList())
            }
        }
    }

    private fun click(id: Int){
        val intent = Intent(this, DetallePersonajeActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun pintarPersonaje(state: PersonajeState) {
        with(binding) {
            if (state.videojuego != null) {
                tvId.text = state.videojuego.id.toString()
                tvFname.text = state.videojuego.titulo
                tvYear.text = state.videojuego.year.toString()
            }
        }
    }

    private fun configAppBar() {
        binding.topAppBar.setOnMenuItemClickListener{menuItem->
            when(menuItem.itemId){
                R.id.add -> {
                    val intent = Intent(this, NewPersonajeActivity::class.java)
                    intent.putExtra("id", binding.tvId.text.toString().toInt())
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val intent = intent
        if (intent.hasExtra("id")) {
            val id = intent.getIntExtra("id", 1)
            viewModel.handleEvent(PersonajeEvent.GetVideojuego(id))
        }
    }


}