package com.example.recyclerretrofitinesmr.ui.main

import android.content.Intent
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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.recyclerretrofitinesmr.R
import com.example.recyclerretrofitinesmr.databinding.ActivityMainBinding
import com.example.recyclerretrofitinesmr.domain.Director
import com.example.recyclerretrofitinesmr.ui.detailDirector.DetailDirectorActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

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
        Timber.tag("DetailDirectorActivity").d("onCreate called")
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            callback = configContextBar()
            directoresAdapter = DirectorAdapter(this@MainActivity,
                object : DirectorAdapter.DirectorActions {
                    override fun onDelete(director: Director) {
                        Timber.tag("DirectorAdapter").d("Director eliminado: %s", director.id)
                        viewModel.handleEvent(MainEvent.DeleteDirector(director))
                        Timber.tag("Directores (MainActivity1)")
                            .d("Directores: " + viewModel.handleEvent(MainEvent.DeleteDirector(director)))
                    }

                    override fun itemClicked(director: Director) {
                        //viewModel.handleEvent(MainEvent.)
                        Timber.tag("MainActivity").d("Item clicked: %s", director.nombre)
                        val intent = Intent(context, DetailDirectorActivity::class.java)
                        intent.putExtra("director", director)
                        context.startActivity(intent)
                    }

                    override fun onStartSelectedMode(director: Director) {
                        viewModel.handleEvent(MainEvent.StartSelectMode)
                        viewModel.handleEvent(MainEvent.SeleccionaDirector(director))
                    }

                }
            )
            rvDirectores.adapter = directoresAdapter
            rvDirectores.layoutManager = GridLayoutManager(this@MainActivity, 1)

            val touchHelper = ItemTouchHelper(directoresAdapter.swipeGesture)
            touchHelper.attachToRecyclerView(rvDirectores)
            viewModel.handleEvent(MainEvent.GetAllDirectores)
            viewModel.uiState.observe(this@MainActivity) { state ->
                Timber.tag("Directores(MainActivity2)").d("Directores: %s", state.directores)
                if (state.directores != anteriorState?.directores && state.directores.isNotEmpty()) {
                    directoresAdapter.submitList(state.directores)
                    Timber.tag("Directores(MainActivity3)")
                        .d("Directores Size: %s", state.directores.size)
                    Timber.tag("Directores(MainActivity4)").d("Directores: %s", state.directores)
                    Timber.tag("Directores(MainActivity5)")
                        .d("Directores Size: %s", directoresAdapter.itemCount)
                }
                if (state.directoresSeleccionados != anteriorState?.directoresSeleccionados) {
                    directoresAdapter.setSelectedItems(state.directoresSeleccionados)
                    actionMode?.title =
                        "${state.directoresSeleccionados.size} selected"
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
            viewModel.sharedFlow.collect() { error ->
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
                        viewModel.handleEvent(MainEvent.DeleteDirectoresSeleccionados())
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

                newText?.let { filtro ->
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