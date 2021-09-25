package com.saratms.clickstask.di

import android.content.Context
import com.saratms.clickstask.R
import com.saratms.clickstask.core.NewsRepository
import com.saratms.clickstask.data.NewsApiService
import com.saratms.clickstask.ui.newsList.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


private const val BASE_URL = "https://newsapi.org/v2/"

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideNewsApi(): NewsApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApiService::class.java)

    @Singleton
    @Provides
    fun provideNewsRepository(newsApiService: NewsApiService): NewsRepository =
        NewsRepositoryImpl(newsApiService)

    @Singleton
    @Provides
    @Named("api_key")
    fun provideApiKey(@ApplicationContext context: Context): String =
        context.getString(R.string.apikey)

}