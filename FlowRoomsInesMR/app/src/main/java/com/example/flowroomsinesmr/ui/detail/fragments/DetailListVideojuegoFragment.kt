package com.example.flowroomsinesmr.ui.detail.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowroomsinesmr.databinding.FragmentDetailVideojuegoListBinding
import com.example.flowroomsinesmr.ui.detail.DetailContract
import com.example.flowroomsinesmr.ui.detail.viewmodels.DetailVideojuegoViewModel
import com.example.flowroomsinesmr.ui.detail.adapters.VideojuegoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class DetailListVideojuegoFragment : Fragment() {

    private var _binding: FragmentDetailVideojuegoListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailVideojuegoViewModel by viewModels()
    private lateinit var videojuegoAdapter: VideojuegoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailVideojuegoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        viewModel.handleEvent(DetailContract.DetailVideojuegoEvent.GetVideojuegos)
        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { value ->
                    binding.loading.visibility = if (value.isLoading) View.VISIBLE else View.GONE
                    videojuegoAdapter.submitList(value.videojuegos)
                    value.error.let {
                        //TODO: METER AQUÍ UN TOAST DEL ERROR
                        viewModel.handleEvent(DetailContract.DetailVideojuegoEvent.ErrorVisto)
                    }

                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                withContext(Dispatchers.Main.immediate) {
                    viewModel.uiState.collect {
                        //Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun init() {

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvVideojuegos.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            binding.rvVideojuegos.context,
            layoutManager.orientation
        )
        binding.rvVideojuegos.addItemDecoration(dividerItemDecoration)

        videojuegoAdapter = VideojuegoAdapter(requireContext())
        binding.rvVideojuegos.adapter = videojuegoAdapter


    }


}