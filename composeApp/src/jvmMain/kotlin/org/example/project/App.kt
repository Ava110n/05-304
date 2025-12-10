package org.example.project


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.TextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.collections.listOf

import kotlin.math.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun App() {
    var parameters by remember {
        mutableStateOf(
            Parameters(0f, 0f, -1.5f, 1.5f, -1.5f, 1.5f)
        )
    }

    Row {
        Canvas(
            Modifier.fillMaxSize().background(Color.LightGray)

        ) {
            parameters.width = size.width
            parameters.height = size.height
            parameters.ifChangedX()


            drawMandelbrot(parameters)
            drawAxis(parameters)
        }
    }
}


fun Mandelbrot(c: Cartesian): Int{
    var maxIter = 5000
    var R = 2.0
    var complex = Complex(c)
    var z = Complex(0f, 0f)
    var iter = 0
    while(iter < maxIter && z.abs()<R){
        z = z.pow(2) + complex
        iter++
    }
    return iter
}
fun DrawScope.drawMandelbrot(parameters: Parameters){
    for( i in 0 .. parameters.width.toInt()){
        for(j in 0 .. parameters.height.toInt()){
            var cartesian = Cartesian(Screen(i,j), parameters)
            var clr = Mandelbrot(cartesian)
            if(clr==5000)
                drawCircle(
                    color = Color.Black,
                    radius = 1f,
                    center = Screen(i,j).toOffset()
                )
        }
    }
}


fun DrawScope.drawAxis(parameters: Parameters) {
    drawLine(
        Color.Red,
        Screen(Cartesian(parameters.xMin, 0f), parameters).toOffset(),
        Screen(Cartesian(parameters.xMax, 0f), parameters).toOffset()
    )

    drawLine(
        Color.Red,
        Screen(Cartesian(0f, parameters.yMin), parameters).toOffset(),
        Screen(Cartesian(0f, parameters.yMax), parameters).toOffset()
    )

    /**drawCircle(
        color = Color.Black, radius = 5f,
        center = Screen(Cartesian(1f, 0f), parameters).toOffset()
    )

    drawCircle(
        color = Color.Black, radius = 5f,
        center = Screen(Cartesian(0f, 1f), parameters).toOffset()
    )*/
}
/*
fun DrawScope.drawLagrange(points: List<Cartesian>, lagrange: Lagrange, parameters: Parameters) {
    for (i in 0 until size.width.toInt()) {
        var xStart = Cartesian(Screen(i, 0), parameters).x
        var xEnd = Cartesian(Screen(i + 1, 0), parameters).x

        drawLine(
            color = Color.Blue,
            start = Screen(
                Cartesian(xStart, lagrange.evaluate(xStart).toFloat()),
                parameters
            ).toOffset(),
            end = Screen(Cartesian(xEnd, lagrange.evaluate(xEnd).toFloat()), parameters).toOffset()
        )

    }
}

@Composable
fun axis(parameters: Parameters) {
    var flag by remember { mutableStateOf(true) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = flag, onClick = { flag = !flag })
        Text("X")
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = !flag, onClick = { flag = !flag })
        Text("Y")
    }

    if (flag) {
        Text("xMin")
        TextField(
            value = parameters.xMin.toString(),
            onValueChange = {
                parameters.xMin = it.toFloatOrNull() ?: -5f
                parameters.ifChangedX()
            }
        )
        Text("xMax")
        TextField(
            value = parameters.xMax.toString(),
            onValueChange = {
                parameters.xMax = it.toFloatOrNull() ?: 5f
                parameters.ifChangedX()
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
                parameters.ifChangedY()
            }
        )
        Text("yMax")
        TextField(
            value = parameters.yMax.toString(),
            onValueChange = {
                parameters.yMax = it.toFloatOrNull() ?: 5f
                parameters.ifChangedY()
            }
        )
        Text("xMin")
        Text(parameters.xMin.toString())
        Text("xMax")
        Text(parameters.xMax.toString())
    }
}

@Composable
fun integrate(parameters: Parameters) {

    var upper_limit_of_integration by remember { mutableStateOf(parameters.yMax) }
    var lower_limit_of_integration by remember { mutableStateOf(parameters.yMin) }
    var splits by remember { mutableStateOf(3) }

    Text("Пределы интегрирования")
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("от")
        TextField(
            value = lower_limit_of_integration.toString(),
            onValueChange = {
                lower_limit_of_integration = it.toFloatOrNull() ?: 5f
            }
        )
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("до")
        TextField(
            value = upper_limit_of_integration.toString(),
            onValueChange = {
                upper_limit_of_integration = it.toFloatOrNull() ?: 5f
            }
        )
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("количество разбиений")
        TextField(
            value = splits.toString(),
            onValueChange = {
                splits = it.toIntOrNull() ?: 3
            }
        )
    }
    Button(
        onClick = {}
    ) { Text("Вычислить интеграл и построить") }

}

fun DrawScope.drawTrapezoids(lagrange: Lagrange, parameters: Parameters) {
    for (i in 0 until size.width.toInt() step 20) {
        val xStart = Cartesian(Screen(i, 0), parameters).x
        val xEnd = Cartesian(Screen(i + 20, 0), parameters).x
        val yStart = lagrange.evaluate(xStart).toFloat()
        val yEnd = lagrange.evaluate(xEnd).toFloat()

        val start = Screen(Cartesian(xStart, yStart), parameters)
        val end = Screen(Cartesian(xEnd, yEnd), parameters)

        val path = Path().apply {
            moveTo(start.x.toFloat(), start.y.toFloat())
            lineTo(end.x.toFloat(), end.y.toFloat())
            lineTo(end.x.toFloat(), Screen(Cartesian(0f, 0f), parameters).toOffset().y)
            lineTo(start.x.toFloat(), Screen(Cartesian(0f, 0f), parameters).toOffset().y)
            close()
        }

        drawPath(path, Color.Blue, style = Stroke(width = 3f))
    }
}
*/