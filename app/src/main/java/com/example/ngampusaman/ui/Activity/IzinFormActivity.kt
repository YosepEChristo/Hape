package com.example.ngampusaman.ui.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.ngampusaman.R
import com.example.ngampusaman.databinding.ActivityIzinFormBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class IzinFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIzinFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIzinFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}