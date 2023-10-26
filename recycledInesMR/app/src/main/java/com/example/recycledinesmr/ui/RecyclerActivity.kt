package com.example.recycledinesmr.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.example.recycledinesmr.R

class RecyclerActivity : AppCompatActivity() {

    private fun click (titulo:String){
        Snackbar.make(findViewById<RecyclerView>(R.id.rvPeliculas)
            , " $titulo", Snackbar.LENGTH_SHORT).show()
    }

}