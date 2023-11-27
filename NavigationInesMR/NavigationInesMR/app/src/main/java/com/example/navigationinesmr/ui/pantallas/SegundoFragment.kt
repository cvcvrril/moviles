package com.example.navigationinesmr.ui.pantallas

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.navigationinesmr.R
import com.example.navigationinesmr.databinding.FragmentSegundoBinding


class SegundoFragment : Fragment(),MenuProvider {

    private var _binding : FragmentSegundoBinding? = null
    private val binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentSegundoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_segundo_fragment, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.primerFragment -> {
                val action = SegundoFragmentDirections.actionSegundoFragmentToPrimerFragment(binding.texto.text.toString())
                findNavController().navigate(action)
                true
            }
            R.id.tercerFragment -> {
                val action = SegundoFragmentDirections.actionSegundoFragmentToTercerFragment(binding.texto.text.toString())
                findNavController().navigate(action)
                true
            }
            else -> false
        }
    }
}