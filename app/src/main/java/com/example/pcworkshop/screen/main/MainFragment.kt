package com.example.pcworkshop.screen.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.pcworkshop.R
import com.example.pcworkshop.databinding.FragmentMainBinding
import com.example.pcworkshop.screen.clients.ClientsFragment
import com.example.pcworkshop.screen.employees.EmployeesFragment
import com.example.pcworkshop.screen.management.ManagementFragment
import com.example.pcworkshop.screen.orders.OrdersFragment
import com.example.pcworkshop.screen.pc.PcFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment: Fragment(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCurrentFragment(ManagementFragment())
        binding?.bottomNavView?.setOnNavigationItemSelectedListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

//    private fun performAction() {
//        when(action.actions) {
//             "management" -> setCurrentFragment(ManagementFragment())
//             "clients" -> setCurrentFragment(ClientsFragment())
//        }
//    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.itemManagement -> setCurrentFragment(ManagementFragment())
            R.id.itemClients -> setCurrentFragment(ClientsFragment())
            R.id.itemOrders -> setCurrentFragment(OrdersFragment())
            R.id.itemPc -> setCurrentFragment(PcFragment())
            R.id.itemEmployees -> setCurrentFragment(EmployeesFragment())
        }
        return true
    }

    fun setCurrentFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction().apply {
            replace(R.id.mainContainer,fragment)
            commit()
        }
    }

    fun showBottomNav() {
        binding?.bottomNavView?.visibility = View.VISIBLE
    }

    fun hideBottomNav() {
        binding?.bottomNavView?.visibility = View.INVISIBLE
    }

}