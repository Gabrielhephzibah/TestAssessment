package com.cherish.technologyassessment.data.dataStore

import com.cherish.technologyassessment.data.model.PopularArticleItem
import com.cherish.technologyassessment.data.remote.ApiService
import com.cherish.technologyassessment.utils.ResultManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularArticleRepository  @Inject constructor(private val apiService: ApiService) {
    fun getAllArticles(mPeriod:String, mApiKey : String)  =
        flow<ResultManager<PopularArticleItem>> {
            val list = apiService.getAllPopularArticle(mPeriod,mApiKey)
            emit(ResultManager.Success(list))
            emit(ResultManager.Loading(false))
        }.handleRest()

    fun <T> Flow<ResultManager<T>>.handleRest(): Flow<ResultManager<T>> {
        return this.flowOn(Dispatchers.IO)
            .catch { emit(ResultManager.Failure(it)) }
            .onStart { emit(ResultManager.Loading(true)) }
            .onCompletion {
                emit(ResultManager.Loading(false))
            }
    }

}