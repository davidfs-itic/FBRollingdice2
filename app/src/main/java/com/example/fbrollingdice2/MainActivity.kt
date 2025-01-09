package com.example.firebase_rollingdice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fbrollingdice2.R
import com.example.fbrollingdice2.databinding.ActivityMainBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.ktx.Firebase

import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = Firebase.analytics

        binding.txtDice1.text= getString(R.string.novaluestring)
        binding.txtDice2.text=getString(R.string.novaluestring)
        binding.txtTotal.text=getString(R.string.novaluestring)
        binding.btnRoll.setOnClickListener{
            val dice1= Random.nextInt(1,6)
            val dice2= Random.nextInt(1,6)
            val sum =dice1+dice2
            firebaseAnalytics.logEvent("RollDice",{
                param("Dice1",dice1.toLong())
                param("Dice2",dice2.toLong())
                param("Double",(dice1==dice2).toString())
            })
            binding.txtDice1.text=dice1.toString()
            binding.txtDice2.text=dice2.toString()
            binding.txtTotal.text=sum.toString()
        }
    }

}