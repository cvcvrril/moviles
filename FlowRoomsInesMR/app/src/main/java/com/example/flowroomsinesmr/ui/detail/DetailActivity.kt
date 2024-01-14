package com.example.flowroomsinesmr.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flowroomsinesmr.databinding.ActivityDetailBinding
import com.example.flowroomsinesmr.databinding.ActivityDetailPruebaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity(){

    private lateinit var binding: ActivityDetailPruebaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPruebaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}