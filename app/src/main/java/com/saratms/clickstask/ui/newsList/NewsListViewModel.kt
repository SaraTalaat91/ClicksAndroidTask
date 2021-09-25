package com.saratms.clickstask.ui.newsList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saratms.clickstask.core.NewsRepository
import com.saratms.clickstask.core.State
import com.saratms.clickstask.core.models.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsListViewModel @ViewModelInject constructor
    (private val newsRepository: NewsRepository) : ViewModel() {

    private val _newsState: MutableLiveData<State<List<News>>> = MutableLiveData()
    val newsState: LiveData<State<List<News>>> = _newsState

    private val _allNewsList: MutableLiveData<List<News>> = MutableLiveData()
    val allNewsList: LiveData<List<News>> = _allNewsList

    fun getNews(locale: String = "eg", apiKey: String) {
        _newsState.value = (State.LoadingState)
        viewModelScope.launch(Dispatchers.IO) {
            val result = newsRepository.getNewsFromApi(locale, apiKey)
            if (result is State.DataState) {
                _allNewsList.value = result.data.filter { true }
            }
            _newsState.postValue(result)
        }
    }

    fun filterByKeyword(keyword: String){
        val filteredList = _allNewsList.value?.filter { it.equals(keyword)}
        _newsState.value = State.DataState(filteredList) as State.DataState<List<News>>
    }
}