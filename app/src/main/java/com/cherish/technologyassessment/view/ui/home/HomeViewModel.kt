package com.cherish.technologyassessment.view.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cherish.technologyassessment.BuildConfig
import com.cherish.technologyassessment.data.dataStore.PopularArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: PopularArticleRepository) : ViewModel() {
    fun getAllPopularArticles() =
        repo.getAllArticles("7", BuildConfig.API_KEY).asLiveData()

}