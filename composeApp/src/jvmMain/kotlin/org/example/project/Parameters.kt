package org.example.project


import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class Parameters(width: Float, height: Float, xMin: Float, xMax: Float, yMin: Float, yMax: Float) {
    var width by mutableStateOf(width)
    var height by mutableStateOf(height)
    var xMin by mutableStateOf(xMin)
    var xMax by mutableStateOf(xMax)
    var yMin by mutableStateOf(yMin)
    var yMax by mutableStateOf(yMax)

    init {
        //ifChangeX()
    }

    fun ifChangedX() {
        val pixelsCount = width / (xMax - xMin)
        val y = height / pixelsCount
        val yMean = (yMax + yMin) / 2
        yMin = yMean - y / 2
        yMax = yMean + y / 2
    }

    fun ifChangedY() {
        val pixelsCount = height / (yMax - yMin)
        val x = width / pixelsCount
        val xMean = (xMax + xMin) / 2
        xMin = xMean - x / 2
        xMax = xMean + x / 2
    }


}