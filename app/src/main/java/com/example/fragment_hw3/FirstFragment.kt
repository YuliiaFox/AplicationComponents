package com.example.fragment_hw3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.fragment_hw3.databinding.FirstFragmentBinding

class FirstFragment : Fragment() {
    private val viewModel: DataViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FirstFragmentBinding.inflate(layoutInflater)
        binding.changeColorButton.setOnClickListener {
            viewModel.loadColors()
        }
        binding.switchFragmentsButton.setOnClickListener {
            viewModel.apply {
                loadSwitchState()
            }
        }
        return binding.root
    }
}