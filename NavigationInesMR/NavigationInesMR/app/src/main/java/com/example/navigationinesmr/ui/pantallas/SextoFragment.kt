package com.example.navigationinesmr.ui.pantallas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.navigationinesmr.R
import com.example.navigationinesmr.databinding.FragmentCuartoBinding
import com.example.navigationinesmr.databinding.FragmentSextoBinding

class SextoFragment : Fragment(){

    private var _binding: FragmentSextoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSextoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.irCuarto.setOnClickListener{
            val action = SextoFragmentDirections.actionSextoFragmentToCuartoFragment(binding.texto.text.toString())
            findNavController().navigate(action)
        }
        binding.irQuinto.setOnClickListener{
            val action = SextoFragmentDirections.actionSextoFragmentToQuintoFragment(binding.texto.text.toString())
            findNavController().navigate(action)
        }
    }
}