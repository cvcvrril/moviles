package com.example.flowroomsinesmr.ui.detail.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowroomsinesmr.databinding.FragmentDetailVideojuegoListBinding
import com.example.flowroomsinesmr.ui.detail.DetailContract
import com.example.flowroomsinesmr.ui.detail.DetailViewModel
import com.example.flowroomsinesmr.ui.detail.adapters.VideojuegoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailListVideojuegoFragment : Fragment() {

    private var _binding: FragmentDetailVideojuegoListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()
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
        videojuegoAdapter = VideojuegoAdapter(requireContext())
        binding.rvVideojuegos.adapter = videojuegoAdapter
        viewModel.handleEvent(DetailContract.DetailEvent.GetVideojuegos)
    }

}