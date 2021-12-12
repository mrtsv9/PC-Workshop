package com.example.pcworkshop.screen.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pcworkshop.databinding.FragmentManagementBinding
import com.example.pcworkshop.screen.clients.ClientsAddingFragment
import com.example.pcworkshop.screen.main.MainFragment
import com.example.pcworkshop.screen.main.MainFragmentDirections

class ManagementFragment: Fragment() {

    private var binding: FragmentManagementBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManagementBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnClients?.setOnClickListener {
            findNavController().navigate(ManagementFragmentDirections.actionManagementFragmentToClientsAddingFragment())
//            (Fragment() as? MainFragment)?.setCurrentFragment(ClientsAddingFragment())
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}