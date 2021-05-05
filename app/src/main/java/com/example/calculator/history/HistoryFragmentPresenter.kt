package com.example.calculator.history

import javax.inject.Inject

class HistoryFragmentPresenter @Inject constructor(
    private val historyView: HistoryView,
    private val reader: HistoryReader
) : HistoryPresenter {

    override fun loadScreen() {
        val historyList = reader.readHistory()
        if (historyList.isEmpty()) {
            historyView.onShowEmptyHistoryMessage()
        } else {
            historyView.onShowHistory(reverseList(historyList))
        }
    }

    private fun reverseList(list: List<String>): List<String> {
        return list.asReversed()
    }
}