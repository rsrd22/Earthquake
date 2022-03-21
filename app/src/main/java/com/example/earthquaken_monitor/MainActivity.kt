package com.example.earthquaken_monitor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earthquaken_monitor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.eqRecycler.layoutManager = LinearLayoutManager(this)

        val eqList = mutableListOf<Earthquake>()
        eqList.add(Earthquake("1", "Buenos Aires", 4.3, 24141125,-102.25546,28.32656))
        eqList.add(Earthquake("2", "Lima", 2.9, 24141125,-102.25546,28.32656))
        eqList.add(Earthquake("3", "Ciudad de México", 3.2, 24141125,-102.25546,28.32656))
        eqList.add(Earthquake("4", "Bogotá", 5.2, 24141125,-102.25546,28.32656))
        eqList.add(Earthquake("5", "Caracas", 1.2, 24141125,-102.25546,28.32656))
        eqList.add(Earthquake("6", "Madrid", 0.9, 24141125,-102.25546,28.32656))
        eqList.add(Earthquake("7", "Medellin", 2.7, 24141125,-102.25546,28.32656))
        eqList.add(Earthquake("8", "Brasilia", 3.7, 24141125,-102.25546,28.32656))
        eqList.add(Earthquake("9", "Montevideo", 4.5, 24141125,-102.25546,28.32656))

        val adapter = EqAdapter()
        binding.eqRecycler.adapter = adapter
        adapter.submitList(eqList)
    }
}