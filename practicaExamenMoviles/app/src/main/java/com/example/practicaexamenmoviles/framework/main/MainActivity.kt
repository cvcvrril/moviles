package com.example.practicaexamenmoviles.framework.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.practicaexamenmoviles.databinding.ActivityMainBinding
import com.example.practicaexamenmoviles.domain.model.Videojuego
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var videojuegoAdapter: VideojuegoAdapter
    private val anteriorState: MainState? = null
    private val viewModel: MainViewModel by viewModels()

    private val callback by lazy {
        configContextBar()
    }

    private var actionMode: ActionMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        videojuegoAdapter = VideojuegoAdapter(this,
            object : VideojuegoAdapter.VideojuegoActions{
                override fun onDelete(videojuego: Videojuego) {
                    TODO("Not yet implemented")
                }

                override fun onStrartSelectMode(videojuego: Videojuego) {
                    TODO("Not yet implemented")
                }

                override fun itemHasClicked(videojuego: Videojuego) {
                    TODO("Not yet implemented")
                }

                override fun onClickItem(idVideojuego: Int) {
                    TODO("Not yet implemented")
                }

            })
        with(binding){
            rvCustomers.adapter = videojuegoAdapter
            rvCustomers.layoutManager= GridLayoutManager(this@MainActivity,1)
        }

        val touchHelper = ItemTouchHelper(videojuegoAdapter.swipeGesture)
        touchHelper.attachToRecyclerView(binding.rvCustomers)
        observarViewModel()
        viewModel.handleEvent(MainEvent.GetVideojuegos)
        configAppBar()

    }

    private fun configAppBar() {
        TODO("Not yet implemented")
    }

    private fun observarViewModel() {
        TODO("Not yet implemented")
    }

    private fun configContextBar() {
        TODO("Not yet implemented")
    }

}