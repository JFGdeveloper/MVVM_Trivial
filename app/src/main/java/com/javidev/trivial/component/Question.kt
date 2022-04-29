package com.javidev.trivial.component

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javidev.trivial.model.QuestionItem
import com.javidev.trivial.presentation.screens.QuestionViewModel
import com.javidev.trivial.util.Colors
import kotlin.Exception

@Composable
fun Question(viewModel: QuestionViewModel) {

    var questions = viewModel.data.value.data?.toMutableList()
    var indexState = remember { mutableStateOf(0)}

    if (viewModel.data.value.loading == true) {
        Log.d("Loading", "Questions is Loading:....")
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Surface(modifier = Modifier.size(70.dp)) {
                CircularProgressIndicator()
            }
        }

    } else {

        val question = try {
            questions?.get(indexState.value)!!
        }catch (ex: Exception){
            null
        }

        if (question != null) QuestionDisplay(question = question, questionIndex =  indexState, viewModel = viewModel)

    }
}

@Composable
fun QuestionDisplay(
    question: QuestionItem,
    questionIndex: MutableState<Int>,
    viewModel: QuestionViewModel,
    onNextClicked: (Int) -> Unit = {}
) {

    // esto es lo que le da la discontinuidad a la linea
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
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
            DrawDottedLine(pathEffect = pathEffect)

            Column {
                Text(text = question.question,
                    modifier = Modifier
                        .padding(6.dp)
                        .align(alignment = Alignment.Start)
                        .fillMaxHeight(0.3f),
                    fontSize = 17.sp,
                    color = Colors.mOffWhite,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 22.sp)
            }

        }

    }
}

@Preview
@Composable
fun QuestionTracker(counter: Int = 10, outOf: Int = 100) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
                withStyle(
                    style = SpanStyle(
                        color = Colors.mLightGray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 27.sp
                    )
                ) {
                    append("Question $counter/")
                    withStyle(
                        style = SpanStyle(
                            color = Colors.mLightGray,
                            fontWeight = FontWeight.Light,
                            fontSize = 14.sp
                        )
                    ) {
                        append("$outOf")
                    }

                }
            }
        },
        modifier = Modifier.padding(20.dp)
    )

}


@Composable
fun DrawDottedLine(pathEffect: PathEffect) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(5.dp),
    ) {
        drawLine(
            color = Colors.mLightGray,
            start = Offset(0f, 0f),
            end = Offset(size.width, y = 0f),
            pathEffect = pathEffect
        )

    }

}