package com.saratms.clickstask.core

import com.saratms.clickstask.data.models.NewsResponse
import retrofit2.Response

interface NewsRepository {
    suspend fun getNewsFromApi(locale: String, apiKey: String): Response<NewsResponse>
}