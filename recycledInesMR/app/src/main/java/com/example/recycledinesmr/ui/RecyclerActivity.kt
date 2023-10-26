package com.example.recycledinesmr.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.example.recycledinesmr.R
import com.example.recycledinesmr.data.Repository

class RecyclerActivity : AppCompatActivity() {

    private fun click (titulo:String){
        Snackbar.make(findViewById<RecyclerView>(R.id.rvPeliculas)
            , " $titulo", Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycled)

        intent.extras?.let {  }

        val listaPeliculas = Repository(assets.open("data.json")).getLista()
//        Toast.makeText(this, "el título es ${listaPeliculas[0].titulo}", Toast.LENGTH_SHORT).show()

        val rvPeliculas = this.findViewById<RecyclerView>(R.id.rvPeliculas)

        Snackbar.make(rvPeliculas, "El título es ${listaPeliculas[0].titulo}", Snackbar.LENGTH_SHORT).show()
        var adapter = PeliculasAdapter(listaPeliculas, ::click)

        listaPeliculas.let {
            rvPeliculas.adapter =adapter
            rvPeliculas.layoutManager = GridLayoutManager(this@RecyclerActivity, 2)
        }

    }
}