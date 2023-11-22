    package com.example.fragmentosinesmr.fragmentos

    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.fragment.app.Fragment
    import com.example.fragmentosinesmr.databinding.FragmentPrimerBinding

    class PrimerFragment : Fragment() {

        private var _binding: FragmentPrimerBinding? = null
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            _binding = FragmentPrimerBinding.inflate(inflater, container, false)
            return binding.root
        }

    }