package com.example.calculator.calculator

interface CalculatorPresenter {
    fun calculateResult(exp: String)
    fun onClear()
    fun onDelete(exp: String)
}