package com.example.calculator.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calculator.databinding.HistoryFragmentBinding
import com.example.calculator.history.recycler.HistoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment : Fragment(), HistoryView {
    private lateinit var binding: HistoryFragmentBinding

    @Inject
    lateinit var presenter: HistoryPresenter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HistoryFragmentBinding.inflate(layoutInflater)
        presenter.loadScreen()
        return binding.root
    }

    override fun onShowHistory(history: List<String>) {
        val recyclerView = binding.historyList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = HistoryAdapter(history)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun onShowEmptyHistoryMessage() {
        binding.emptyHistoryMessage.visibility = View.VISIBLE
        binding.historyList.visibility = View.GONE
    }
}