package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var txt: EditText;
    private lateinit var button: Button;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt = findViewById(R.id.editTextText)
        button = findViewById(R.id.button)
        button.setOnClickListener {
            Toast.makeText(
                this,
                "${getString(R.string.el_usuario_ha_puesto_esto)} ${txt.text}",
                Toast.LENGTH_LONG
            ).show()

        }
    }
}