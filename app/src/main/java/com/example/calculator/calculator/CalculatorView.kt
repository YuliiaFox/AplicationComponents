package com.example.calculator.calculator

interface CalculatorView {
    fun showResult(res: String)
    fun clearInput()
    fun updateInputView(newExp: String)
}