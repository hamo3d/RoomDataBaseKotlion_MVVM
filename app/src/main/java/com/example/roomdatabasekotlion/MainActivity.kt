package com.example.roomdatabasekotlion

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.roomdatabasekotlion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)





    }
}