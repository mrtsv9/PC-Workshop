package com.example.pcworkshop


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pcworkshop.databinding.FragmentRegisterBinding
import com.example.pcworkshop.services.Test

class RegisterFragment: Fragment() {

    private var binding: FragmentRegisterBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn = binding!!.btnSingIn
        btn.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment3())
        }

    }

    override fun onResume() {
        super.onResume()
        Test.start()
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}