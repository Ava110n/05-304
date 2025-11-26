package org.example.project

class Cartesian {
    var x: Float
    var y: Float

    constructor(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    constructor(screen: Screen, parameters: Parameters) {
        this.x =
            parameters.xMin +
                    screen.x * (parameters.xMax - parameters.xMin) /
                    parameters.width
        this.y =
            parameters.yMax -
                    screen.y * (parameters.yMax - parameters.yMin) /
                    parameters.height
    }
}