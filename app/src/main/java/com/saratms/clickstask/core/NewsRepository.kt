package com.saratms.clickstask.core

import com.saratms.clickstask.core.models.News

interface NewsRepository {
    suspend fun getNewsFromApi(locale: String, apiKey: String): State<List<News>>
}