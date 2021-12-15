package com.example.pcworkshop.screen.clients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.pcworkshop.databinding.FragmentClientsAddingBinding
import com.example.pcworkshop.models.clients.Clients
import com.example.pcworkshop.models.clients.PostClient
import com.example.pcworkshop.screen.clients.repository.ClientsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClientsAddingFragment : Fragment() {

    private var binding: FragmentClientsAddingBinding? = null
    private val repository = ClientsRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientsAddingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (clientToUpdate == null) {
            binding?.btnAddClient?.setOnClickListener {
                clickListener()
            }
        } else {
            setFields()
            binding?.btnAddClient?.setOnClickListener {
                updateClient()
                clearAll()
            }

            binding?.btnDeleteClient?.setOnClickListener {
                deleteClient()
                clearAll()
            }
        }

    }

    private fun clickListener() {
        if(checkAllFields()) {
            val name = binding?.etAddingClientsName?.text.toString()
            val lastName = binding?.etAddingClientsLastName?.text.toString()
            val email = binding?.etAddingClientsEmail?.text.toString()
            val phoneNumber = binding?.etAddingClientsPhone?.text.toString()
            val password = binding?.etAddingClientsPassword?.text.toString()

            val client = PostClient(0, name, lastName, email, phoneNumber, password)

            val call = repository.addClient(client)
            call.enqueue(object : Callback<PostClient> {
                override fun onResponse(call: Call<PostClient>, response: Response<PostClient>) {
                    Toast.makeText(context, "Клиент успешно добавлен!", Toast.LENGTH_SHORT).show()
//                    MainFragment().setCurrentFragment(ClientsFragment())
                    findNavController().popBackStack()
                }

                override fun onFailure(call: Call<PostClient>, t: Throwable) {
//                    findNavController().navigate(ClientsAddingFragmentDirections.actionClientsAddingFragmentToClientsFragment())
//                    (Fragment() as MainFragment).setCurrentFragment(ClientsFragment())
                    Toast.makeText(context, "Клиент успешно добавлен!", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            })
        }
        else {
            Toast.makeText(context, "Заполните все поля верно!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFields() {
        binding?.btnAddClient?.text = "Редактировать клиента"
        binding?.btnDeleteClient?.visibility = View.VISIBLE

        binding?.etAddingClientsName?.setText(clientToUpdate?.firstName)
        binding?.etAddingClientsLastName?.setText(clientToUpdate?.lastName)
        binding?.etAddingClientsEmail?.setText(clientToUpdate?.email)
        binding?.etAddingClientsPhone?.setText(clientToUpdate?.phoneNumber)
        binding?.etAddingClientsPassword?.setText(clientToUpdate?.password)
    }

    private fun updateClient() {
        if(checkAllFields()) {
            val name = binding?.etAddingClientsName?.text.toString()
            val lastName = binding?.etAddingClientsLastName?.text.toString()
            val email = binding?.etAddingClientsEmail?.text.toString()
            val phoneNumber = binding?.etAddingClientsPhone?.text.toString()
            val password = binding?.etAddingClientsPassword?.text.toString()

            val call =
                clientToUpdate?.clientId?.let {
                    repository.updateClient(
                        it, name, lastName, email,
                        phoneNumber, password)
                }
            call?.enqueue(object: Callback<PostClient> {
                override fun onResponse(call: Call<PostClient>, response: Response<PostClient>) {
                    Toast.makeText(context, "Клиент успешно изменен!", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }

                override fun onFailure(call: Call<PostClient>, t: Throwable) {
                    Toast.makeText(context, "Клиент успешно изменен!", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            } )
        }
        else {
            Toast.makeText(context, "Заполните все поля верно!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteClient() {
        clientToUpdate?.let { repository.deleteClient(it.clientId) }
            ?.enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    Toast.makeText(context, "Клиент успешно удален!", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Toast.makeText(context, "Клиент успешно удален!", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            })
    }

    private fun checkAllFields(): Boolean {
        if (binding?.etAddingClientsName?.text.isNullOrEmpty()
            || binding?.etAddingClientsLastName?.text.isNullOrEmpty()
            || binding?.etAddingClientsEmail?.text.isNullOrEmpty()
            || binding?.etAddingClientsPhone?.text.isNullOrEmpty()
            || binding?.etAddingClientsPassword?.text.isNullOrEmpty())
            return false

        return true
    }


    private fun clearAll() {
        clientToUpdate = null
        binding?.btnAddClient?.text = "Добавить клиента"
        binding?.btnDeleteClient?.visibility = View.GONE

        binding?.etAddingClientsName?.text?.clear()
        binding?.etAddingClientsLastName?.text?.clear()
        binding?.etAddingClientsEmail?.text?.clear()
        binding?.etAddingClientsPhone?.text?.clear()
        binding?.etAddingClientsPassword?.text?.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        var clientToUpdate: Clients? = null
    }

}