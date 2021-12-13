package com.example.pcworkshop.screen.types_of_accessories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pcworkshop.R
import com.example.pcworkshop.databinding.FragmentTypesOfAccessoriesBinding
import com.example.pcworkshop.models.positions.PostPosition
import com.example.pcworkshop.models.types_of_accessories.PostType
import com.example.pcworkshop.screen.positions.adapters.PositionsAdapter
import com.example.pcworkshop.screen.types_of_accessories.repository.TypesOfAccessoriesRepository
import com.example.pcworkshop.screen.types_of_accessories.view_models.TypesOfAccessoriesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TypesOfAccessoriesFragment : Fragment() {

    private var binding: FragmentTypesOfAccessoriesBinding? = null
    private val repository = TypesOfAccessoriesRepository()
    private val viewModel: TypesOfAccessoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTypesOfAccessoriesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TypesAdapter()
        binding?.rvTypes?.layoutManager = LinearLayoutManager(binding?.root?.context,
            LinearLayoutManager.VERTICAL, false)
        binding?.rvTypes?.adapter = adapter

        viewModel.getAllTypesOfAccessories()
        viewModel.typesOfAccessoriesLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        binding?.btnAddType?.setOnClickListener {
            clickListener()
        }
    }

    private fun clickListener() {
        val typeName = binding?.etAddingPosition?.text.toString()
        val validationList = listOf<String>(typeName)

        if(checkFieldsValidation(validationList)) {
            val call = repository.addTypeOfAccessory(PostType(typeName))

            call.enqueue(object: Callback<PostType> {
                override fun onResponse(call: Call<PostType>, response: Response<PostType>) {
                    Toast.makeText(context, "Тип комплектующих успешно добавлен!", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }

                override fun onFailure(call: Call<PostType>, t: Throwable) {
                    Toast.makeText(context, "Тип комплектующих успешно добавлен!", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            })
        }
    }

    private fun checkFieldsValidation(list: List<String>): Boolean {
        list.forEach {
            if(it.isEmpty() || it.length > 20) {
                Toast.makeText(context,  "Заполните все полня корректно!", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}