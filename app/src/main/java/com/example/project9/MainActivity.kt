package com.example.project9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.project9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) { //this screen is the host of all fragments. it displays them all.
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //initiates binding
        val view = binding.root
        setContentView(view)
        supportFragmentManager.beginTransaction().replace(R.id.nav,loginScreen()).commit()

    }
}