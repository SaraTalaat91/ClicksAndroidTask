package com.saratms.clickstask.data

import com.saratms.clickstask.data.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") locale: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse>
}