package com.example.fbrollingdice2


import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.example.fbrollingdice2.databinding.ActivityStatsBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.DefaultValueFormatter

class statsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatsBinding
    private var statsSaved:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.statstoolbar)
        supportActionBar?.title =MainApp.nomAplicacio
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.txtTotalTirades.text=MainApp.estadistica.tirades.toString()

        val entries = ArrayList<BarEntry>()
        for (i in MainApp.estadistica.daus.indices) {
            entries.add(BarEntry((i+1).toFloat(), MainApp.estadistica.daus[i].toFloat()))
        }

        val barDataSet = BarDataSet(entries, "Resultats Daus")

        val pastelColors = listOf(
            Color.rgb(255, 204, 204), // Rosa clar
            Color.rgb(204, 229, 255), // Blau clar
            Color.rgb(204, 255, 204), // Verd clar
            Color.rgb(255, 255, 204), // Groc clar
            Color.rgb(255, 229, 204), // Taronja clar
            Color.rgb(229, 204, 255)  // Lila clar
        )
        barDataSet.colors=pastelColors
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16f
        barDataSet.valueFormatter= DefaultValueFormatter(0)

        binding.barchartDaus.apply {
            data= BarData(barDataSet)
            //setDrawGridBackground(false)

            xAxis.setDrawGridLines(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            axisLeft.setDrawGridLines(false)
            axisRight.setDrawAxisLine(false)
            axisRight.setDrawGridLines(false)
            axisRight.setDrawLabels(false)
            description.text="Freqüència daus"
            description.isEnabled = true // Activa la descripció
            setFitBars(true) // Ajusta les barres al gràfic
            animateY(1000) // Animació en Y
            invalidate()
        }


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_estadistica, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.menuGuardar)
        {
            //Guardar
            //
            this.statsSaved=true
            //Mostrar info usuari
            return true
        }else return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        menu?.findItem(R.id.menuGuardar)?.setEnabled(!this.statsSaved)


        return super.onPrepareOptionsMenu(menu)
    }
}