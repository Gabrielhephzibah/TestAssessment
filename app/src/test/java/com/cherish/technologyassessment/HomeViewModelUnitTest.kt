package com.cherish.technologyassessment

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cherish.technologyassessment.data.dataStore.PopularArticleRepository
import com.cherish.technologyassessment.view.ui.home.HomeViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelUnitTest {
    @get:Rule
    var executorRule = InstantTaskExecutorRule()

    private lateinit var homeViewModel: HomeViewModel
    lateinit var repository: PopularArticleRepository
    @Before
    fun setUp(){
        val repo = Mockito.mock(PopularArticleRepository::class.java)
        homeViewModel = HomeViewModel(repo)

    }


}