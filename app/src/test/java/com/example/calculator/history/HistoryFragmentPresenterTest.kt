package com.example.calculator.history

import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class HistoryFragmentPresenterTest {
    private val reader: HistoryReader = mock()
    private val historyView: HistoryView = mock()
    private lateinit var classUnderTest: HistoryPresenter

    @Before
    fun setup() {
        classUnderTest = HistoryFragmentPresenter(historyView, reader)
    }

    @Test
    fun testLoadScreen_ifHistoryIsEmpty() {
        val historyList = emptyList<String>()
        whenever(reader.readHistory()).thenReturn(historyList)

        classUnderTest.loadScreen()

        verify(historyView).onShowEmptyHistoryMessage()
    }

    @Test
    fun testLoadScreen_ifHistoryNotEmpty() {
        val historyList = listOf("1", "2", "3")
        whenever(reader.readHistory()).thenReturn(historyList)

        classUnderTest.loadScreen()

        verify(historyView).onShowHistory(historyList.reversed())
    }
}