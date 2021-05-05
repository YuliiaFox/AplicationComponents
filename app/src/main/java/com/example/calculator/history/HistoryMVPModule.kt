package com.example.calculator.history

import androidx.fragment.app.Fragment
import com.example.calculator.HistoryRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
abstract class HistoryMVPModule {
    @Binds
    abstract fun bindPresenter(historyFragmentPresenter: HistoryFragmentPresenter): HistoryPresenter

    @Binds
    abstract fun bindView(historyFragment: HistoryFragment): HistoryView

    @Binds
    abstract fun bindHistoryReader(reader: HistoryRepository): HistoryReader
}

@Module
@InstallIn(FragmentComponent::class)
object HistoryModule {
    @Provides
    fun provideHistoryFragment(fragment: Fragment): HistoryFragment {
        return fragment as HistoryFragment
    }
}