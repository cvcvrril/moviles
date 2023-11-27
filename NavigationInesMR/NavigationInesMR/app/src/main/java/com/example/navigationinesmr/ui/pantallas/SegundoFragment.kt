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


class SegundoFragment : Fragment(),MenuProvider {

    private var _binding : FragmentSegundoBinding? = null
    private val binding get() = _binding!!
    private var isOriginalMenu = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentSegundoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        if (isOriginalMenu) {
            menuInflater.inflate(R.menu.menu_segundo_fragment, menu)
        }
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.tercerFragment -> {
                val action = SegundoFragmentDirections.actionSegundoFragmentToTercerFragment()
                findNavController().navigate(action)
                true
            }
            R.id.cuartoFragment -> {
                val action = SegundoFragmentDirections.actionSegundoFragmentToCuartoFragment()
                findNavController().navigate(action)
                true
            }

            else -> false
        }
    }
}