package com.javidev.trivial.repository

import android.util.Log
import com.javidev.trivial.data.Data0rException
import com.javidev.trivial.model.QuestionItem
import com.javidev.trivial.network.QuestionApi
import java.lang.Exception
import javax.inject.Inject

class QuestionRepository
@Inject constructor(private val api: QuestionApi){
    // estamos usando el wraper que he creado en data
    private val data0rException = Data0rException<ArrayList<QuestionItem>,Boolean,Exception>()

    suspend fun getAllQuestions():Data0rException<ArrayList<QuestionItem>,Boolean,Exception>{
        try {
            data0rException.loading = true
            data0rException.data = api.getAllQuestion()
            if (data0rException.data.toString().isNotEmpty()) data0rException.loading = false

        }catch (exception: Exception){
            data0rException.e = exception
            Log.d("exception","getAllQuestion: ${data0rException.e!!.localizedMessage}")
        }

        return data0rException
    }
}