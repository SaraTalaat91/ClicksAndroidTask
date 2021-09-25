package com.saratms.clickstask.ui.newsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.saratms.clickstask.R
import com.saratms.clickstask.core.State
import com.saratms.clickstask.databinding.NewsListFragmentBinding
import com.saratms.clickstask.ui.newsListList.NewsListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : Fragment() {
    lateinit var binding: NewsListFragmentBinding
    lateinit var newsAdapter: NewsListAdapter
    val newsListViewModel: NewsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(keyword: String): Boolean {
                newsListViewModel.filterByKeyword(keyword)
                return false
            }
        })


        newsAdapter = NewsListAdapter(requireContext(), mutableListOf())
        binding.newsRecycler.adapter = newsAdapter
        binding.newsRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupObservers() {
        newsListViewModel.newsState.observe(viewLifecycleOwner) {
            when (it) {
                is State.LoadingState -> {
                    binding.loadingProgressBar.visibility = View.VISIBLE
                    binding.newsRecycler.visibility = View.GONE
                    binding.searchView.visibility = View.GONE
                    binding.emptyTv.visibility = View.GONE
                }

                is State.DataState -> {
                    binding.loadingProgressBar.visibility = View.GONE
                    if (it.data.isEmpty()) {
                        binding.newsRecycler.visibility = View.GONE
                        binding.emptyTv.text = getString(R.string.empty_view_statement)
                        binding.emptyTv.visibility = View.VISIBLE
                    } else {
                        binding.newsRecycler.visibility = View.VISIBLE
                        binding.searchView.visibility = View.VISIBLE
                        binding.emptyTv.visibility = View.GONE
                        newsAdapter.updateList(it.data)
                    }
                }

                is State.ErrorState -> {
                    binding.loadingProgressBar.visibility = View.GONE
                    binding.newsRecycler.visibility = View.GONE
                    binding.searchView.visibility = View.GONE
                    binding.emptyTv.text = it.exception.message
                    binding.emptyTv.visibility = View.VISIBLE
                }
            }
        }
    }

}