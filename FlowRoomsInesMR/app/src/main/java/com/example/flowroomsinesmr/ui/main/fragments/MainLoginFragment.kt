package com.example.flowroomsinesmr.ui.main.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.flowroomsinesmr.databinding.FragmentMainLoginBinding
import com.example.flowroomsinesmr.ui.detail.DetailActivity
import com.example.flowroomsinesmr.ui.main.events.MainLoginEvent
import com.example.flowroomsinesmr.ui.main.viewmodels.MainLoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainLoginFragment : Fragment(){

    private var _binding: FragmentMainLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainLoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonRegistro.setOnClickListener {
            val action = MainLoginFragmentDirections.actionMainLoginFragmentToMainRegistroFragment()
            findNavController().navigate(action)
        }

        binding.buttonLogin.setOnClickListener {
            val usuario = binding.textUser.text.toString()
            val contrasena = binding.textPassword.text.toString()

            if (usuario.isNotEmpty() && contrasena.isNotEmpty()) {
                login(usuario, contrasena)
                viewModel.operacionExitosa.observe(viewLifecycleOwner){ exitoso ->
                    if (exitoso){
                        Toast.makeText(requireContext(), "Registro exitoso", Toast.LENGTH_SHORT).show()
                        binding.textUser.text.clear()
                        binding.textPassword.text.clear()
                        val intent = Intent(requireContext(), DetailActivity::class.java)
                        startActivity(intent)
                        this@MainLoginFragment.activity?.finish()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun login(usuario: String, contrasena: String) {
        viewModel.handleEvent(MainLoginEvent.GetLogin(usuario, contrasena))
    }

}