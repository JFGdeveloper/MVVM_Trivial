package com.javidev.trivial.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javidev.trivial.presentation.screens.QuestionViewModel
import com.javidev.trivial.util.Colors

@Composable
fun Question(viewModel: QuestionViewModel) {

    var questions = viewModel.data.value.data?.toMutableList()

    if (viewModel.data.value.loading == true) {
        Log.d("Loading", "Questions is Loading:....")
    } else {
        QuestionDisplay()

        Log.d("Loadin", "Questions is stopped")
        Log.d("Loading", "Size: ${questions?.size}")
        questions?.forEach {
            Log.d("Loading", "Question: ${it.question}")
        }


    }
}


@Composable
fun QuestionDisplay() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
            color = Colors.mDarkPurple // he creado mis propios colores en un objeto en pack util
    ) {
        Column(
            modifier = Modifier.padding(4.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            QuestionTracker()

        }

    }
}

@Preview
@Composable
fun QuestionTracker(counter: Int = 10,
                    outOf: Int = 100) {
    Text(text = buildAnnotatedString {
        withStyle(style = ParagraphStyle(textIndent = TextIndent.None)){
            withStyle(style = SpanStyle(color = Colors.mLightGray,
                fontWeight = FontWeight.Bold,
                fontSize = 27.sp)
            ){
                append("Question $counter/")
                withStyle(style = SpanStyle(color = Colors.mLightGray,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp)){
                    append("$outOf")
                }

            }
        }
    },
        modifier = Modifier.padding(20.dp))

}