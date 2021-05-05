package com.example.calculator.history

interface HistoryView {
    fun onShowHistory(history: List<String>)
    fun onShowEmptyHistoryMessage()
}