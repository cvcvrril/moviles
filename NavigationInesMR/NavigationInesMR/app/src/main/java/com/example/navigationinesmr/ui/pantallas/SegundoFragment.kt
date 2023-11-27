package com.example.navigationinesmr.ui.pantallas

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.navigationinesmr.R
import com.example.navigationinesmr.databinding.FragmentSegundoBinding


class SegundoFragment : Fragment() {

    private var _binding : FragmentSegundoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentSegundoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.irNoveno.setOnClickListener{
            val action = SegundoFragmentDirections.actionSegundoFragmentToNovenoFragment()
            findNavController().navigate(action)
        }
    }
}