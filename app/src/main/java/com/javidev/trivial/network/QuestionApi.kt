package com.javidev.trivial.network

import com.javidev.trivial.model.Question
import retrofit2.http.GET

interface QuestionApi {
    @GET("world.json")
    suspend fun getAllQuestion():Question
}