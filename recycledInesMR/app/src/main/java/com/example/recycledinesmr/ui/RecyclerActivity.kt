package com.example.recycledinesmr.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        Toast.makeText(this, "el título es ${listaPeliculas[0].titulo}", Toast.LENGTH_SHORT).show()

        val rvPeliculas = this.findViewById<RecyclerView>(R.id.rvPeliculas)

        Snackbar.make(rvPeliculas, "${listaPeliculas[0].titulo}", Snackbar)

    }
}