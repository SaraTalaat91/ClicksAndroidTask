package com.saratms.clickstask.ui.newsList

import com.saratms.clickstask.core.NewsRepository
import com.saratms.clickstask.data.NewsApiService
import com.saratms.clickstask.data.models.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService) : NewsRepository {

    override suspend fun getNewsFromApi(locale: String, apiKey: String): Response<NewsResponse> {
        return newsApiService.getTopHeadlines(locale, apiKey)
    }
}