package com.example.navigationinesmr.ui.pantallas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationinesmr.databinding.FragmentQuintoBinding


class QuintoFragment : Fragment(){

    private var _binding: FragmentQuintoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuintoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.irCuarto.setOnClickListener{
            val action = QuintoFragmentDirections.actionQuintoFragmentToCuartoFragment()
            findNavController().navigate(action)
        }
        binding.irSexto.setOnClickListener{
            val action = QuintoFragmentDirections.actionQuintoFragmentToSextoFragment()
            findNavController().navigate(action)
        }
    }
}