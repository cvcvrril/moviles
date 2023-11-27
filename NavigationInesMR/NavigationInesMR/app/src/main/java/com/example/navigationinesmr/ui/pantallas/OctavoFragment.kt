package com.example.navigationinesmr.ui.pantallas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationinesmr.databinding.FragmentOctavoBinding
import com.example.navigationinesmr.databinding.FragmentSeptimoBinding

class OctavoFragment : Fragment() {

    private var _binding : FragmentOctavoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOctavoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.irCuarto.setOnClickListener{
            val action = OctavoFragmentDirections.actionOctavoFragmentToCuartoFragment()
            findNavController().navigate(action)
        }
        binding.irSeptimo2.setOnClickListener{
            val action = OctavoFragmentDirections.actionOctavoFragmentToSeptimoFragment()
            findNavController().navigate(action)
        }
    }


}