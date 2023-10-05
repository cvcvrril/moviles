package com.example.formulariobien.ui.pantallaMain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.formulariobien.databinding.ActivityMainBinding
import com.example.formulariobien.domain.usecases.personas.AddPeliculasUseCase
import com.example.formulariobien.utils.StringProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels{
        MainViewModel.MainViewModelFactory(
            StringProvider.instance(this),
            AddPeliculasUseCase()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply{
            setContentView(root)
        }
        eventos()
        observarViewModel()
    }

    private fun observarViewModel(){
        viewModel.uiState.observe(this@MainActivity){ state ->
            state.error?.let { error->
                Toast.makeText(this@MainActivity, error, Toast.LENGTH_LONG).show()
                viewModel.errorMostrado()
            }
            if (state.error == null)
                binding.editTextTextPersonName.setText(state.pelicula.titulo)

        }
    }

    private fun eventos(){
        with(binding){
            button.setOnClickListener{
                viewModel.addPersona(Persona(editTextPersonName.text.toString))
            }
        }

    }

}