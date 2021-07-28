package com.cherish.technologyassessment.data.remote

import com.cherish.technologyassessment.BuildConfig
import com.cherish.technologyassessment.data.model.PopularArticleItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("{period}.json?")
    suspend fun getAllPopularArticle(@Path("period") period : String,
                                     @Query("api-key") apikey : String): PopularArticleItem
}