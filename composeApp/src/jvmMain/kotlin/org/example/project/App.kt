package org.example.project


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.onClick
import androidx.compose.material.RadioButton
import androidx.compose.material.TextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import org.jetbrains.compose.ui.tooling.preview.Preview

import kotlin.math.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun App() {
    var parameters by remember {
        mutableStateOf(
            Parameters(0f, 0f, -5f, 5f, -5f, 5f)
        )
    }
    var strip by remember { mutableStateOf(false) }
    var flag by remember { mutableStateOf(true) }

    var points by remember { mutableStateOf(mutableListOf<Cartesian>())}

    Row {
        Column(modifier = Modifier.weight(3f)) {
            Canvas(
                Modifier.fillMaxSize().background(Color.LightGray).
                onPointerEvent(PointerEventType.Press){
                    points.add(Cartesian(it.changes.first().position, parameters))
                }
            ) {
                for(point in points){
                    drawCircle(Color.Yellow, radius = 5f, center = Screen(point, parameters).toOffset())
                }

                parameters.width = size.width
                parameters.height = size.height
                //parameters.ifChangeX()
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

                drawCircle(
                    color = Color.Black, radius = 5f,
                    center = Screen(Cartesian(1f, 0f), parameters).toOffset()
                )

                drawCircle(
                    color = Color.Black, radius = 5f,
                    center = Screen(Cartesian(0f, 1f), parameters).toOffset()
                )


                for (i in 0 until size.width.toInt()) {
                    var xStart = Cartesian(Screen(i, 0), parameters).x
                    var xEnd = Cartesian(Screen(i + 1, 0), parameters).x

                    drawLine(
                        color = Color.Red,
                        start = Screen(Cartesian(xStart, function(xStart)), parameters).toOffset(),
                        end = Screen(Cartesian(xEnd, function(xEnd)), parameters).toOffset()
                    )

                }

            }


        }
        if (strip) {
            Column(modifier = Modifier.weight(1f)) {
                Row {
                    RadioButton(selected = flag, onClick = { flag = !flag })
                    Text("X")
                }
                Row {
                    RadioButton(selected = !flag, onClick = { flag = !flag })
                    Text("Y")
                }

                if (flag) {
                    Text("xMin")
                    TextField(
                        value = parameters.xMin.toString(),
                        onValueChange = {
                            parameters.xMin = it.toFloatOrNull() ?: -5f
                            parameters.ifChangeX()
                        }
                    )
                    Text("xMax")
                    TextField(
                        value = parameters.xMax.toString(),
                        onValueChange = {
                            parameters.xMax = it.toFloatOrNull() ?: 5f
                            parameters.ifChangeX()
                        }
                    )
                    Text("yMin")
                    Text(parameters.yMin.toString())
                    Text("yMax")
                    Text(parameters.yMax.toString())
                } else {
                    Text("yMin")
                    TextField(
                        value = parameters.yMin.toString(),
                        onValueChange = {
                            parameters.yMin = it.toFloatOrNull() ?: -5f
                            parameters.ifChangeY()
                        }
                    )
                    Text("yMax")
                    TextField(
                        value = parameters.yMax.toString(),
                        onValueChange = {
                            parameters.yMax = it.toFloatOrNull() ?: 5f
                            parameters.ifChangeY()
                        }
                    )
                    Text("xMin")
                    Text(parameters.xMin.toString())
                    Text("xMax")
                    Text(parameters.xMax.toString())
                }

            }
        }

    }
    Switch(checked = strip, onCheckedChange = { strip = !strip })
}

fun function(x: Float) = sin(x)