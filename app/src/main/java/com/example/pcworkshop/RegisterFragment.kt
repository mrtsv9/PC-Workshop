package com.example.pcworkshop


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pcworkshop.databinding.FragmentRegisterBinding
import com.example.pcworkshop.services.Test
import com.example.pcworkshop.clients.Client
import com.example.pcworkshop.services.TestPost

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

            val firstName = binding?.etName?.text.toString()
            val lastName = binding?.etLastName?.text.toString()
            val email = binding?.etEmail?.text.toString()
            val phoneNumber = binding?.etPhoneNumber?.text.toString()
            val password = binding?.etPassword?.text.toString()
            val client = Client(0, firstName, lastName, email, phoneNumber, password)
            val post = TestPost(client)
            post.start()
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