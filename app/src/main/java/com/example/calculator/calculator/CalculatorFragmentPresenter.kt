package com.example.calculator.calculator

import org.mariuszgromada.math.mxparser.Expression
import javax.inject.Inject


class CalculatorFragmentPresenter @Inject constructor(
    private val calculatorView: CalculatorView,
    private val writer: HistoryWriter
) : CalculatorPresenter {

    override fun calculateResult(exp: String) {
        if (exp.isNotBlank()) {
            val res = Expression(exp).calculate().toString()
            calculatorView.showResult(res)
            writer.writeHistory(joinExpAndResult(exp, res))
        }
    }

    override fun onClear() {
        calculatorView.clearInput()
    }

    override fun onDelete(exp: String) {
        if (exp.isNotBlank()) {
            calculatorView.updateInputView(deleteLastSymbol(exp))
        }
    }

    private fun joinExpAndResult(exp: String, res: String): String {
        return "$exp = $res"
    }

    private fun deleteLastSymbol(string: String): String {
        return string.dropLast(1)
    }
}