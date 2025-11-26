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

    fun ifChangeX() {
        var pixelsCount = width / (xMax - xMin)
        var y = height / pixelsCount
        yMin = (yMax + yMin) / 2 - y / 2
        yMax = (yMax + yMin) / 2 + y / 2
    }

    fun ifChangeY() {
        var pixelsCount = height / (yMax - yMin)
        var x = width / pixelsCount
        xMin = (xMax + xMin) / 2 - x / 2
        xMax = (xMax + xMin) / 2 + x / 2
    }

}