package org.example.project

import kotlin.math.*

class Complex {
    var x: Float
    var y: Float
    constructor(x: Float, y: Float){
        this.x = x
        this.y = y
    }
    constructor(c: Cartesian){
        this.x = c.x
        this.y = c.y
    }
    operator fun times(other: Complex):Complex{
        return Complex(
            this.x*other.x-this.y*other.y,
            this.y*other.x+this.x*other.y
        )
    }
    operator fun plus(other: Complex): Complex{
        return Complex(
            this.x+other.x,
            this.y+other.y
        )
    }
    fun pow(n: Int): Complex{
        var result = Complex(this.x, this.y)
        for(i in 0 until abs(n)){
            result *= result
        }
        return result
    }
    fun abs() = sqrt(this.x*this.x + this.y*this.y)
}