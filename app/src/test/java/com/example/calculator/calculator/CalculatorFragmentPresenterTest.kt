package com.example.calculator.calculator

import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyZeroInteractions

class CalculatorFragmentPresenterTest {
    private val writer: HistoryWriter = mock()
    private val calculatorView: CalculatorView = mock()
    private lateinit var classUnderTest: CalculatorPresenter

    @Before
    fun setup() {
        classUnderTest = CalculatorFragmentPresenter(calculatorView, writer)
    }

    @Test
    fun testCalculateResult_ifExpIsBlank() {
        val exp = ""

        classUnderTest.calculateResult(exp)

        verifyZeroInteractions(calculatorView)
        verifyZeroInteractions(writer)
    }

    @Test
    fun testCalculateResult_ifExpNotBlank() {
        val exp = "1+2"
        val res = "3.0"
        val joinedRes = "1+2 = 3.0"

        classUnderTest.calculateResult(exp)

        verify(calculatorView).showResult(res)
        verify(writer).writeHistory(joinedRes)
    }

    @Test
    fun testOnClear() {
        classUnderTest.onClear()

        verify(calculatorView).clearInput()
    }

    @Test
    fun testOnDelete_ifExpNotBlank() {
        val exp = "12"
        val res = "1"

        classUnderTest.onDelete(exp)

        verify(calculatorView).updateInputView(res)
    }

    @Test
    fun testOnDelete_ifExpIsBlank() {
        val exp = ""

        classUnderTest.onDelete(exp)

        verifyZeroInteractions(calculatorView)
    }
}