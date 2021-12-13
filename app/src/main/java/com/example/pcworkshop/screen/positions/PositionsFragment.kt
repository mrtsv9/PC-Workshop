package com.example.pcworkshop.screen.positions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pcworkshop.databinding.FragmentPositionsBinding
import com.example.pcworkshop.models.positions.PostPosition
import com.example.pcworkshop.screen.positions.adapters.PositionsAdapter
import com.example.pcworkshop.screen.types_of_accessories.repository.TypesOfAccessoriesRepository
import com.example.pcworkshop.screen.positions.view_models.PositionsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PositionsFragment : Fragment() {

    private var binding: FragmentPositionsBinding? = null
    private val repository = TypesOfAccessoriesRepository()
    private val viewModel: PositionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPositionsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PositionsAdapter()
        binding?.rvPositions?.layoutManager = LinearLayoutManager(binding?.root?.context,
            LinearLayoutManager.VERTICAL, false)
        binding?.rvPositions?.adapter = adapter

        viewModel.getAllPositions()
        viewModel.positionsLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        binding?.btnAddPosition?.setOnClickListener {
            clickListener()
        }

    }

    private fun clickListener() {
        val positionName = binding?.etAddingPosition?.text.toString()
        val validationList = listOf<String>(positionName)

        if(checkFieldsValidation(validationList)) {
            val call = repository.addPosition(PostPosition(positionName))

            call.enqueue(object: Callback<PostPosition> {
                override fun onResponse(call: Call<PostPosition>,response: Response<PostPosition>) {
                    Toast.makeText(context, "Должность успешно добавлена!", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }

                override fun onFailure(call: Call<PostPosition>, t: Throwable) {
                    Toast.makeText(context, "Должность успешно добавлена!", Toast.LENGTH_SHORT).show()
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