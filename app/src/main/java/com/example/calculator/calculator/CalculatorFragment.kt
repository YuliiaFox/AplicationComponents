package com.example.calculator.calculator

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.calculator.R
import com.example.calculator.calculator.CalculatorFragmentDirections.Companion.openHistory
import com.example.calculator.databinding.CalculatorFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CalculatorFragment : Fragment(), CalculatorView {
    private lateinit var binding: CalculatorFragmentBinding

    @Inject
    lateinit var calculatorPresenter: CalculatorPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = CalculatorFragmentBinding.inflate(layoutInflater)
        val onClick = View.OnClickListener {
            when (it) {
                binding.clearBtn -> calculatorPresenter.onClear()
                binding.deleteBtn -> calculatorPresenter.onDelete(binding.calculatorInputView.text.toString())
                binding.equals -> calculatorPresenter
                    .calculateResult(binding.calculatorInputView.text.toString())
                else -> binding.calculatorInputView.text.append((it as Button).text)

            }
        }
        binding.keyboard.children.forEach { it.setOnClickListener(onClick) }
        return binding.root
    }

    override fun showResult(res: String) {
        binding.result.text = res
    }

    override fun clearInput() {
        binding.calculatorInputView.text.clear()
    }

    override fun updateInputView(newExp: String) {
        binding.calculatorInputView.setText(newExp)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.calculator_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.history_menu -> {
                findNavController().navigate(openHistory())
            }
            else -> return false
        }
        return true
    }
}