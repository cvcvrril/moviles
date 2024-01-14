package com.example.flowroomsinesmr.ui.detail.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flowroomsinesmr.databinding.FragmentDetailJugadorListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailListJugadorFragment : Fragment(){

    private var _binding: FragmentDetailJugadorListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailJugadorListBinding.inflate(inflater, container, false)
        return binding.root
    }

}