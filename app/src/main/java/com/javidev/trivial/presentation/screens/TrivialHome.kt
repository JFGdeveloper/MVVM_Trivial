package com.javidev.trivial.presentation.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.javidev.trivial.presentation.component.Question

@Composable
 fun TrivialHome(viewModel: QuestionViewModel = hiltViewModel()) {
    Question(viewModel = viewModel)
}
