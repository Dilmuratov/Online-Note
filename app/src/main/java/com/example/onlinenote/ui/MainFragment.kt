package com.example.onlinenote.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.onlinenote.R
import com.example.onlinenote.databinding.FragmentMainBinding
import com.example.onlinenote.presentation.NetworkViewModel
import com.example.onlinenote.ui.adapter.NoteAdapter
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: NetworkViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val adapter = NoteAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        viewModel = ViewModelProvider(this)[NetworkViewModel::class.java]

        initVariables()

        initObserves()

        addNote()

        refreshRecyclerView()

        searchNote()

        toNote()
    }
    private fun initVariables() {
        binding.recyclerView.adapter = adapter
    }

    private fun initObserves() {
        lifecycleScope.launch {
            viewModel.getAllTodo.observe(requireActivity()) {
                adapter.submitList(it)
            }
        }

        lifecycleScope.launch {
            viewModel.getAllNotes()
        }
    }

    private fun addNote() {
        binding.btnAdd.setOnClickListener {
            val bundle = Bundle().apply { putString("noteId", "-1") }
            findNavController().navigate(R.id.action_mainFragment_to_addFragment, bundle)
        }
    }

    private fun refreshRecyclerView() {
        swipeRefreshLayout = binding.swipeRefreshLayout

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true

            initObserves()

            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun searchNote() {
        binding.ivSearch.setOnClickListener {
            binding.expandableLayout.isExpanded = binding.expandableLayout.isExpanded.not()

            if (binding.expandableLayout.isExpanded) {
                binding.etSearch.addTextChangedListener {
                    if (it.toString().isNotEmpty()) {
                        lifecycleScope.launch {
                            viewModel.searchNotes(it.toString())
                        }
                    } else {
                        lifecycleScope.launch {
                            viewModel.getAllNotes()
                        }
                    }
                }
            }
        }
    }

    private fun toNote() {
        adapter.setOnItemClickListener {
            Log.d(
                "TTTT",
                "Id: ${it.id}. Title: ${it.title}. Text: ${it.text}. LastUpdatedDate: ${it.lastUpdatedDate}."
            )

            val noteId = it.id
            val title = it.title
            val text = it.text
            val lastUpdatedDate = it.lastUpdatedDate
            val bundle = Bundle()
            bundle.putString("noteId", noteId)
            bundle.putString("title", title)
            bundle.putString("text", text)
            bundle.putString("lastUpdatedDate", lastUpdatedDate)
            findNavController().navigate(R.id.action_mainFragment_to_addFragment, bundle)
        }
    }
}
