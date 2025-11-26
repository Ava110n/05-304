package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    var parameters = Parameters(0f,0f,-5f,5f,-5f,5f)

    Row{
        Column(modifier = Modifier.weight(3f)) {
            Canvas(Modifier.fillMaxSize().background(Color.LightGray)) {
                parameters.width = size.width
                parameters.height = size.height
                drawLine(
                    Color.Black,
                    Screen(Cartesian(parameters.xMin, 0f), parameters).toOffset(),
                    Screen(Cartesian(parameters.xMax, 0f), parameters).toOffset()
                )

                drawLine(
                    Color.Black,
                    Screen(Cartesian(0f, parameters.yMin), parameters).toOffset(),
                    Screen(Cartesian(0f, parameters.yMax), parameters).toOffset()
                )

            }
        }
    }
}