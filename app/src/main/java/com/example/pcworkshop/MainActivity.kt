package com.example.pcworkshop


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.pcworkshop.databinding.ActivityMainBinding
import com.example.pcworkshop.screen.clients.ClientsFragment
import com.example.pcworkshop.screen.employees.EmployeesFragment
import com.example.pcworkshop.screen.management.ManagementFragment
import com.example.pcworkshop.screen.orders.OrdersFragment
import com.example.pcworkshop.screen.pc.PcFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

    }

//    fun hideBottomNavView() {
//        binding?.bottomNavView?.visibility = View.GONE
//    }
//
//    fun showBottomNavView() {
//        binding?.bottomNavView?.visibility = View.VISIBLE
//    }
//
//    fun replaceFragment(fragment: Fragment) {
//
//    }
//
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        val navView = binding?.bottomNavView
//        when(item.itemId) {
//            R.id.itemClients -> setCurrentFragment(ClientsFragment())
//            R.id.itemOrders -> setCurrentFragment(OrdersFragment())
//            R.id.itemPc -> setCurrentFragment(PcFragment())
//            R.id.itemEmployees -> setCurrentFragment(EmployeesFragment())
//        }
//        return true
//    }
//
//    private fun setCurrentFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction().apply {
//            add(R.id.container,fragment)
//            commit()
//        }
//    }


}