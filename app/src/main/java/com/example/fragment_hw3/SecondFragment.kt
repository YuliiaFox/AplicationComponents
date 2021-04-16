package com.example.fragment_hw3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.fragment_hw3.databinding.SecondFragmentBinding

class SecondFragment : Fragment() {
    private val viewModel: DataViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = SecondFragmentBinding.inflate(layoutInflater)
        viewModel.getDataForSecondFragment().observe(viewLifecycleOwner, { color ->
            binding.root.setBackgroundColor(color)
        })
        binding.openDialogButton.setOnClickListener {
            val dialog = CreateListDialog()
            dialog.show(parentFragmentManager, CreateListDialog.TAG)
        }
        return binding.root
    }
}