package com.volynkin.nasamedia.ui


import android.app.Activity
import android.os.Bundle
import com.volynkin.nasaimageandvideolibrary.databinding.ActivityMainBinding


class MainActivity: Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}