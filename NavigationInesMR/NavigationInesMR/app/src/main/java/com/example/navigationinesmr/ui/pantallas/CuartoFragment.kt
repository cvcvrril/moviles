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

class CuartoFragment : Fragment(), MenuProvider {

    private var _binding: FragmentCuartoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCuartoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.irQuinto.setOnClickListener {
            val action = CuartoFragmentDirections.actionCuartoFragmentToQuintoFragment()
            findNavController().navigate(action)
        }
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_cuarto_fragment, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.quintoFragment -> {
                val action = CuartoFragmentDirections.actionCuartoFragmentToQuintoFragment()
                findNavController().navigate(action)
                true
            }
            else -> return super.onOptionsItemSelected(menuItem)
        }
    }
}