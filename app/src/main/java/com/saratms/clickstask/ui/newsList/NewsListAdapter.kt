package com.saratms.clickstask.ui.newsListList

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.saratms.clickstask.core.models.News
import com.saratms.clickstask.databinding.LayoutNewsItemBinding


class NewsListAdapter(val context: Context, private val newsList: MutableList<News>) :
    RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = LayoutNewsItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        with(holder) {
            with(newsList[position]) {
                newsItemBinding.newsTitleTv.setText(source)
                newsItemBinding.newsSourceTv.setText(title)

                val circulatProgressDrawable = CircularProgressDrawable(context)

                with(circulatProgressDrawable){
                    setColorSchemeColors(
                        R.color.holo_blue_light,
                        R.color.holo_blue_dark,
                        R.color.holo_blue_bright
                    )
                    centerRadius = 30f
                    strokeWidth = 5f
                    start()
                }

                Glide.with(context).load(image)
                    .placeholder(circulatProgressDrawable)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(newsItemBinding.newsImageIv)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (newsList.isNotEmpty()) newsList.size else 0
    }

    fun updateList(newsList: List<News>) {
        with(this.newsList) {
            clear()
            addAll(newsList)
        }
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(val newsItemBinding: LayoutNewsItemBinding) :
        RecyclerView.ViewHolder(newsItemBinding.root)
}