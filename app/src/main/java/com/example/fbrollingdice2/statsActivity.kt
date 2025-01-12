package com.example.fbrollingdice2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fbrollingdice2.databinding.ActivityMainBinding
import com.example.fbrollingdice2.databinding.ActivityStatsBinding

class statsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.statstoolbar)
        supportActionBar?.title =MainApp.nomAplicacio
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}