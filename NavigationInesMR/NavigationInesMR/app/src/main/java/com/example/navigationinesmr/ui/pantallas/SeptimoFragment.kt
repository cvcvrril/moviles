package com.example.navigationinesmr.ui.pantallas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationinesmr.databinding.FragmentQuintoBinding
import com.example.navigationinesmr.databinding.FragmentSeptimoBinding

class SeptimoFragment : Fragment() {

    private var _binding : FragmentSeptimoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeptimoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.irTercero.setOnClickListener{
            val action = SeptimoFragmentDirections.actionSeptimoFragmentToTercerFragment()
            findNavController().navigate(action)
        }
        binding.irOctavo.setOnClickListener{
            val action = SeptimoFragmentDirections.actionSeptimoFragmentToOctavoFragment()
            findNavController().navigate(action)
        }
    }


}