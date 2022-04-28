package com.javidev.trivial.presentation.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javidev.trivial.data.Data0rException
import com.javidev.trivial.model.QuestionItem
import com.javidev.trivial.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel
@Inject constructor(private val repo: QuestionRepository): ViewModel() {

     val data: MutableState<Data0rException<ArrayList<QuestionItem>,Boolean,Exception>> =
        mutableStateOf(Data0rException(null,true,Exception("")))


    init {
        getAllQuestion()
    }


    private fun getAllQuestion(){
        viewModelScope.launch {
            data.value.loading = true
            data.value= repo.getAllQuestions()
            if (data.value.data.toString().isNotEmpty()){
                data.value.loading = false
            }
        }
    }
}