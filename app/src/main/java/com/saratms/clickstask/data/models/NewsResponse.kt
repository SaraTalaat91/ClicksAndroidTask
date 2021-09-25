package com.saratms.clickstask.data.models


import com.google.gson.annotations.SerializedName
import com.saratms.clickstask.core.models.News

data class NewsResponse(
    @SerializedName("articles")
    var articles: List<Article>,
    @SerializedName("status")
    var status: String,
    @SerializedName("totalResults")
    var totalResults: Int
) {
    data class Article(
        @SerializedName("author")
        var author: Any,
        @SerializedName("content")
        var content: Any,
        @SerializedName("description")
        var description: String,
        @SerializedName("publishedAt")
        var publishedAt: String,
        @SerializedName("source")
        var source: Source,
        @SerializedName("title")
        var title: String,
        @SerializedName("url")
        var url: String,
        @SerializedName("urlToImage")
        var urlToImage: String
    ) {
        data class Source(
            @SerializedName("id")
            var id: Any,
            @SerializedName("name")
            var name: String
        )
    }
}

fun List<NewsResponse.Article>.mapToNews(): List<News> {
    return this.map { News(it.title, it.urlToImage, it.source.name, it.description) }
}