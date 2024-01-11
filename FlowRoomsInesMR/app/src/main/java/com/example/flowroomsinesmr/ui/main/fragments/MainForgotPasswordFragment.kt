package com.example.flowroomsinesmr.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.flowroomsinesmr.databinding.FragmentMainForgotpasswordBinding
import com.example.flowroomsinesmr.domain.modelo.Credencial
import com.example.flowroomsinesmr.ui.main.events.MainForgotPasswordEvent
import com.example.flowroomsinesmr.ui.main.viewmodels.MainForgotPasswordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainForgotPasswordFragment : Fragment (){

    private var _binding: FragmentMainForgotpasswordBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainForgotPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainForgotpasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonVolverLoginFP.setOnClickListener {
            val action = MainForgotPasswordFragmentDirections.actionMainForgotPasswordFragmentToMainLoginFragment()
            findNavController().navigate(action)
        }

        binding.buttonEnviarFP.setOnClickListener {
            val email = binding.editTextEmailFP.text.toString()
            if (email.isNotEmpty()){
                val credencialForgotPassword : Credencial = Credencial("", "", email, false, "User")
                forgotPassword(credencialForgotPassword)
                binding.editTextEmailFP.text.clear()

            } else {
                Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun forgotPassword(credencial: Credencial){
        viewModel.handleEvent(MainForgotPasswordEvent.ForgotPassword(credencial))
    }

}