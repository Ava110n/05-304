package org.example.project

import androidx.compose.ui.geometry.Offset

class Screen {
    var x: Int
    var y: Int

    constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    constructor(cartesian: Cartesian, parameters: Parameters) {
        this.x = (
                (cartesian.x - parameters.xMin)
                        / (parameters.xMax - parameters.xMin)
                        * parameters.width
                ).toInt()
        this.y = (
                (parameters.yMax - cartesian.y)
                        / (parameters.yMax - parameters.yMin)
                        * parameters.height
                ).toInt()
    }

    fun toOffset(): Offset {
        return Offset(
            x.toFloat(),
            y.toFloat()
        )
    }
}

