package com.saratms.clickstask.ui.newsDetail

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.saratms.clickstask.R
import com.saratms.clickstask.databinding.NewsDetailFragmentBinding

class NewsDetailFragment : Fragment() {

    private val detailsViewModel: NewsDetailViewModel by viewModels()
    private val args: NewsDetailFragmentArgs by navArgs()
    private lateinit var binding: NewsDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewsDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            var news = args.news
            newsTitleTv.setText(news.title)
            newsSourceTv.setText(news.source)
            newsDescTv.setText(news.description)

            Glide.with(requireContext()).load(news.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.color.gray)
                .into(newsDetailIv)

            binding.backIv.setOnClickListener{
                NavHostFragment.findNavController(this@NewsDetailFragment).navigateUp()
            }
        }
    }

}