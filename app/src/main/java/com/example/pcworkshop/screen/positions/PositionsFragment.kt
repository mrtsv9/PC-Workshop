package com.example.pcworkshop.screen.positions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pcworkshop.R
import com.example.pcworkshop.databinding.FragmentPositionsBinding


class PositionsFragment : Fragment() {

    private var binding: FragmentPositionsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPositionsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        binding = null
    }

}