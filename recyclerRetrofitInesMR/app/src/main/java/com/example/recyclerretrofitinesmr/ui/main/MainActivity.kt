package com.example.recyclerretrofitinesmr.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.recyclerretrofitinesmr.databinding.ActivityMainBinding
import com.example.recyclerretrofitinesmr.domain.Director
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var directoresAdapter: DirectorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            directoresAdapter = DirectorAdapter(this@MainActivity,
                object : DirectorAdapter.DirectorActions {
                    override fun onDelete(director: Director) {
                        viewModel.handleEvent(MainEvent.DeleteDirector(director))
                    }

                    override fun itemClicked(director: Director) {
                        TODO("Not yet implemented")
                    }

                    override fun onStartSelectedMode(director: Director) {
                        TODO("Not yet implemented")
                    }

                }
            )
            rvDirectores.adapter = directoresAdapter
            val touchHelper = ItemTouchHelper(directoresAdapter.swipeGesture)
            touchHelper.attachToRecyclerView(rvDirectores)

        }

    }
}