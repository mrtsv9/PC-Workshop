package com.example.pcworkshop.screen.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pcworkshop.databinding.FragmentLoginBinding
import com.example.pcworkshop.models.clients.Clients
import com.example.pcworkshop.models.employees.Employees
import com.example.pcworkshop.screen.clients.repository.ClientsRepository
import com.example.pcworkshop.screen.employees.repository.EmployeesRepository
import com.example.pcworkshop.screen.main.MainFragmentDirections
import com.example.pcworkshop.screen.user.UserFragment.Companion.loggedUserEmail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment: Fragment() {

    private var binding: FragmentLoginBinding? = null
    private val clientsRepository = ClientsRepository()
    private val employeesRepository = EmployeesRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn = binding!!.btnSingIn
        btn.setOnClickListener {
            findUser()
        }
    }

    private fun findUser() {
        val email = binding?.etLoginEmail?.text.toString()
        val password = binding?.etLoginPassword?.text.toString()
        val listOfClients: MutableList<Clients> = emptyList<Clients>().toMutableList()
        CoroutineScope(Dispatchers.IO).launch {
            clientsRepository.getAllClients().body()?.let { listOfClients.addAll(it) }
        }
        Thread.sleep(300)
        listOfClients.forEach { client ->
            if(client.email == email) {
                if (client.password != password) {
                    Toast.makeText(context, "Введен неверный пароль!", Toast.LENGTH_SHORT).show()
                } else if(client.password == password) {
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToUserFragment())
                    loggedUserEmail = client.email
                    return
                }
            }
        }

        findAdmin()
    }

    private fun findAdmin() {
        val email = binding?.etLoginEmail?.text.toString()
        val password = binding?.etLoginPassword?.text.toString()
        val listOfEmployees: MutableList<Employees> = emptyList<Employees>().toMutableList()
        CoroutineScope(Dispatchers.IO).launch {
            employeesRepository.getAllEmployees().body()?.let { listOfEmployees.addAll(it) }
        }
        Thread.sleep(300)
        listOfEmployees.forEach { employee ->
            if (employee.email == email) {
                if (employee.password != password) {
                    Toast.makeText(context, "Введен неверный пароль!", Toast.LENGTH_SHORT).show()
                } else if(employee.password == password)
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
                    return
            }
        }
        Toast.makeText(context, "Пользователь не найден!", Toast.LENGTH_SHORT).show()

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}