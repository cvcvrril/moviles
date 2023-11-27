package com.example.navigationinesmr.ui.pantallas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationinesmr.databinding.FragmentNovenoBinding

class NovenoFragment : Fragment(){

    private var _binding : FragmentNovenoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentNovenoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.irSegundo.setOnClickListener{
            val action = NovenoFragmentDirections.actionNovenoFragmentToSegundoFragment()
            findNavController().navigate(action)
        }
        binding.irDecimo.setOnClickListener{
            val action = NovenoFragmentDirections.actionNovenoFragmentToDecimoFragment()
            findNavController().navigate(action)
        }
    }

}