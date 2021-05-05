package com.example.calculator.linkpage

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.calculator.databinding.LinkFragmentBinding
import com.example.calculator.linkpage.LinkFragmentDirections.Companion.openCalculator

class LinkFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = LinkFragmentBinding.inflate(layoutInflater)
        val spanString = SpannableString("Tap to open calculator").apply {
            setSpan(ForegroundColorSpan(Color.GREEN), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(ForegroundColorSpan(Color.RED), 4, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(ForegroundColorSpan(Color.BLUE), 7, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(ForegroundColorSpan(Color.MAGENTA), 12, 22, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        binding.linkText.text = spanString
        binding.linkText.setOnClickListener {
            findNavController().navigate(openCalculator())
        }
        return binding.root
    }
}