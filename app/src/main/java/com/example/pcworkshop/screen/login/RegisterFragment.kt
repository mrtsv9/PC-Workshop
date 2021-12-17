package com.example.pcworkshop.screen.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pcworkshop.databinding.FragmentRegisterBinding
//import com.example.pcworkshop.services.Test
import com.example.pcworkshop.models.clients.Clients
import com.example.pcworkshop.models.clients.PostClient
import com.example.pcworkshop.screen.clients.repository.ClientsRepository
import com.example.pcworkshop.screen.user.UserFragment.Companion.loggedUserEmail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//import com.example.pcworkshop.z_depcrecated.TestPost

class RegisterFragment: Fragment() {

    private var binding: FragmentRegisterBinding? = null
    private val repository = ClientsRepository()

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


            val firstName = binding?.etName?.text.toString()
            val lastName = binding?.etLastName?.text.toString()
            val email = binding?.etEmail?.text.toString()
            val phoneNumber = binding?.etPhoneNumber?.text.toString()
            val password = binding?.etPassword?.text.toString()
            val client = PostClient(0, firstName, lastName, email, phoneNumber, password)

            val call = repository.addClient(client)
            call.enqueue(object: Callback<PostClient> {
                override fun onResponse(call: Call<PostClient>, response: Response<PostClient>) {
                    Toast.makeText(context, "Вы успешно зарегистрировались!", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<PostClient>, t: Throwable) {
                    Toast.makeText(context, "Вы успешно зарегистрировались!", Toast.LENGTH_SHORT).show()
                }
            })

            loggedUserEmail = email
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToMainFragment())
//            val post = TestPost(client)
//            post.start()
        }

    }

    override fun onResume() {
        super.onResume()
//        Test.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}