package com.example.flowroomsinesmr.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.flowroomsinesmr.databinding.FragmentMainRegisterBinding
import com.example.flowroomsinesmr.domain.modelo.Credencial
import com.example.flowroomsinesmr.ui.main.events.MainRegistroEvent
import com.example.flowroomsinesmr.ui.main.viewmodels.MainRegistroViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainRegistroFragment : Fragment(){

    private var _binding: FragmentMainRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainRegistroViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonVolverLogin.setOnClickListener {
            val action = MainRegistroFragmentDirections.actionMainRegistroFragmentToMainLoginFragment()
            findNavController().navigate(action)
        }

        binding.buttonDoReg.setOnClickListener {
            val usuario = binding.textUserReg.text.toString()
            val contrasena = binding.textPasswordReg.text.toString()
            val email = binding.textEmailReg.text.toString()
            if (usuario.isNotEmpty() && contrasena.isNotEmpty()) {
                val nuevaCredencial : Credencial = Credencial(usuario,contrasena,email,false, "User", "")
                register(nuevaCredencial)

                viewModel.operacionExitosa.observe(viewLifecycleOwner) { exitoso ->
                    if (exitoso) {
                        Toast.makeText(requireContext(), "Registro exitoso", Toast.LENGTH_SHORT).show()
                        binding.textUserReg.text.clear()
                        binding.textPasswordReg.text.clear()
                        binding.textEmailReg.text.clear()
                    } else{
                        Toast.makeText(requireContext(), "Hubo un error", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun register(credencial: Credencial){
        viewModel.handleEvent(MainRegistroEvent.DoRegister(credencial))
    }
}