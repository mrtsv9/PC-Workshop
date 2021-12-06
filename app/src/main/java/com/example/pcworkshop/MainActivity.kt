package com.example.pcworkshop


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.pcworkshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    fun hideBottomNavView() {
        binding?.bottomNavView?.visibility = View.GONE
    }

    fun showBottomNavView() {
        binding?.bottomNavView?.visibility = View.VISIBLE
    }

    fun replaceFragment(fragment: Fragment) {
        
    }
}