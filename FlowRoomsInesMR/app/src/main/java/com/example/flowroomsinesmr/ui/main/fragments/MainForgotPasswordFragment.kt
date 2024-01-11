package com.example.flowroomsinesmr.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flowroomsinesmr.databinding.FragmentMainForgotpasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainForgotPasswordFragment : Fragment (){

    private var _binding: FragmentMainForgotpasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainForgotpasswordBinding.inflate(inflater, container, false)
        return binding.root
    }


}