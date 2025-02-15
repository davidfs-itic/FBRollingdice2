package com.example.fbrollingdice2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fbrollingdice2.databinding.Activitat2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding:Activitat2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=Activitat2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fabafegir.setOnClickListener {

        }
    }
}