package com.javidev.trivial.di

import com.javidev.trivial.network.QuestionApi
import com.javidev.trivial.repository.QuestionRepository
import com.javidev.trivial.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideQuestionRepository(api: QuestionApi) = QuestionRepository(api)


    @Singleton
    @Provides
    fun provideQuestionApi(): QuestionApi{
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionApi::class.java)
    }
}