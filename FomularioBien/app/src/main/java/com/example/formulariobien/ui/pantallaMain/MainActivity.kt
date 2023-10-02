package com.example.formulariobien.ui.pantallaMain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.formulariobien.domain.usecases.personas.AddPeliculasUseCase
import com.example.formulariobien.utils.StringProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModerl by viewModels{
        MainViewModelFactory(
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
        viewModel.uiState.observe(this@MainActivity)
    }

    private fun eventos(){
        with(binding){
            button.setOnClickListener{
                viewModel.addPersona(Persona(editTextPersonName.text.toString))
            }
        }

    }

}