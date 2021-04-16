package com.example.fragment_hw3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.fragment_hw3.databinding.ThirdFragmentBinding

class ThirdFragment : Fragment() {
    private val viewModel: DataViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ThirdFragmentBinding.inflate(layoutInflater)
        viewModel.getDataForThirdFragment().observe(viewLifecycleOwner,
            binding.root::setBackgroundColor
        )
        viewModel.getListText().observe(viewLifecycleOwner) { binding.listText.text = it }
        return binding.root
    }
}