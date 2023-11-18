package com.example.recyclerretrofitinesmr.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.view.ActionMode
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.recyclerretrofitinesmr.R
import com.example.recyclerretrofitinesmr.databinding.ActivityMainBinding
import com.example.recyclerretrofitinesmr.domain.Director
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val context = this
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var directoresAdapter: DirectorAdapter
    private var anteriorState: MainState? = null
    private var primeraVez: Boolean = false
    private var actionMode: ActionMode? = null
    private lateinit var callback: ActionMode.Callback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            callback = configContextBar()
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
            viewModel.handleEvent(MainEvent.GetAllDirectores)
            viewModel.uiState.observe(this@MainActivity) { state ->
                Log.d("Directores(MainActivity)", "Directores: ${state.directores}")
                if (state.directores != anteriorState?.directores && state.directores.isNotEmpty()) {
                    directoresAdapter.submitList(state.directores)
                }
                if (state.selectMode != anteriorState?.selectMode) {
                    if (state.selectMode) {
                        directoresAdapter.startSelectMode()
                        if (primeraVez) {
                            startSupportActionMode(callback)?.let {
                                actionMode = it
                            }
                            primeraVez = false
                        }
                    } else {
                        directoresAdapter.resetSelectMode()
                        primeraVez = true
                        actionMode?.finish()
                    }
                }
                state.error?.let {
                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                    viewModel.handleEvent(MainEvent.ErrorVisto)
                }
                anteriorState = state
            }

        }

        lifecycleScope.launch {
            viewModel.sharedFlow.collect(){ error->
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun configContextBar() =
        object : ActionMode.Callback {

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                menuInflater.inflate(R.menu.context_bar, menu)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return when (item?.itemId) {
                    R.id.favorite -> {
                        // Handle share icon press
                        true
                    }
                    R.id.search -> {
                        // Handle delete icon press
                        true
                    }
                    R.id.more -> {
                        viewModel.handleEvent(MainEvent.DeletePersonasSeleccionadas())
                        true
                    }
                    else -> false
                }
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                viewModel.handleEvent(MainEvent.ResetSelectMode)

            }

        }

    private fun configAppBar() {
        binding.topAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }


        val actionSearch = binding.topAppBar.menu.findItem(R.id.search).actionView as SearchView

        actionSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                newText?.let {filtro ->
                    viewModel.handleEvent(MainEvent.GetDirector(filtro))
                }

                return false
            }


        })

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favorite -> {
                    // Handle favorite icon press
                    true
                }
                R.id.search -> {
                    // Handle search icon press
                    true
                }
                R.id.more -> {
                    // Handle more item (inside overflow menu) press
                    true
                }
                else -> false
            }
        }
    }
}