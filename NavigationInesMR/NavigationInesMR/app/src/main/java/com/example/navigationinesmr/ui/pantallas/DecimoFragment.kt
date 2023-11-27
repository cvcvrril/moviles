package com.example.navigationinesmr.ui.pantallas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationinesmr.databinding.FragmentDecimoBinding

class DecimoFragment : Fragment() {

    private var _binding : FragmentDecimoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentDecimoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.irSegundo2.setOnClickListener{
            val action = DecimoFragmentDirections.actionDecimoFragmentToSegundoFragment()
            findNavController().navigate(action)
        }
        binding.irNoveno2.setOnClickListener{
            val action = DecimoFragmentDirections.actionDecimoFragmentToNovenoFragment()
            findNavController().navigate(action)
        }
    }

}