package com.saratms.clickstask.ui.newsList

import com.saratms.clickstask.core.NewsRepository
import com.saratms.clickstask.core.State
import com.saratms.clickstask.data.NewsApiService
import com.saratms.clickstask.data.models.mapToNews
import com.saratms.clickstask.core.models.News
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService
) : NewsRepository {

    override suspend fun getNewsFromApi(locale: String, apiKey: String): State<List<News>> {
        return try {
            val response = newsApiService.getTopHeadlines(locale, apiKey)
            if (response.isSuccessful && response.body() != null) {
                State.DataState(response.body()?.articles?.mapToNews() ?: emptyList())
            } else {
                State.ErrorState(Exception(response.message()))
            }
        } catch (exception: Exception) {
            State.ErrorState(exception)
        }
    }
}