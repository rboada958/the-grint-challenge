package com.example.redditapp.presentation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redditapp.R
import com.example.redditapp.databinding.FragmentHomeBinding
import com.example.redditapp.domain.entity.Children
import com.example.redditapp.presentation.adapter.HomeAdapter
import com.example.redditapp.utils.PaginationVerticalScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeAdapter.ClickListener {

    private lateinit var viewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        HomeAdapter(mutableListOf(), this)
    }

    private var isLoading = false
    private var isRefresh = false
    private var page = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        binding.recyclerView.adapter = adapter

        viewModel.uiState.observe(viewLifecycleOwner) {
            when(it) {
                is HomeViewModel.UiState.Error -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(requireContext(), it.msg, Toast.LENGTH_SHORT).show()
                }
                HomeViewModel.UiState.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
                is HomeViewModel.UiState.Success -> {
                    page = it.data.after
                    binding.progress.visibility = View.GONE
                    adapter.addItems(it.data.children)
                    isLoading = false
                }
            }
        }

        binding.recyclerView.addOnScrollListener(object  : PaginationVerticalScrollListener(binding.recyclerView.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                isRefresh = true
                viewModel.getAccessToken(page)
                binding.progress.visibility = View.VISIBLE
            }

            override fun isLastPage(): Boolean = false
            override fun isLoading(): Boolean = isLoading
        })
    }

    override fun onClicked(item: Children) {
        findNavController().navigate(R.id.detailsFragment, bundleOf("item" to item))
    }

}