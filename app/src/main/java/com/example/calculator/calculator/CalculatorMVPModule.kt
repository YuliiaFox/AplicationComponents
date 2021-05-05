package com.example.calculator.calculator

import androidx.fragment.app.Fragment
import com.example.calculator.HistoryRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
abstract class CalculatorMVPModule {

    @Binds
    abstract fun bindPresenter(calculatorFragmentPresenter: CalculatorFragmentPresenter): CalculatorPresenter

    @Binds
    abstract fun bindView(calculatorFragment: CalculatorFragment): CalculatorView

    @Binds
    abstract fun bindHistoryWriter(writer: HistoryRepository): HistoryWriter
}

@Module
@InstallIn(FragmentComponent::class)
object CalculatorModule {
    @Provides
    fun provideCalculatorFragment(fragment: Fragment): CalculatorFragment {
        return fragment as CalculatorFragment
    }
}