package com.saratms.clickstask.ui.newsList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.saratms.clickstask.R

class NewsListFragment : Fragment() {

    companion object {
        fun newInstance() = NewsListFragment()
    }

    private lateinit var viewModel: NewsListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}