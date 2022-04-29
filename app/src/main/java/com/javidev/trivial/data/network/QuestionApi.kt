package com.javidev.trivial.data.network

import com.javidev.trivial.data.model.Question
import retrofit2.http.GET

interface QuestionApi {
    @GET("world.json")
    suspend fun getAllQuestion():Question
}