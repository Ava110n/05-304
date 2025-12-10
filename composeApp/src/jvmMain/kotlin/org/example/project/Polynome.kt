package org.example.project

import kotlin.math.*

open class Polynome {
    var coeffs: Array<Double> = arrayOf(0.0)

    constructor(coeffs: Array<Double>){
        this.coeffs = coeffs
    }
    constructor(coeffs: Array<Int>){
        this.coeffs = Array(coeffs.size, {it -> coeffs[it].toDouble()})
    }
    constructor(){
        coeffs = arrayOf(0.0)
    }

    fun clean(){
        var new_size = this.coeffs.size
        for(i in this.coeffs.size-1 downTo 0){
            if(this.coeffs[i] == 0.0){
                new_size--
            }
            else{
                break
            }
        }
        if(new_size==0){this.coeffs = Array(1, {0.0}); return}
        this.coeffs = Array(new_size, {it -> this.coeffs[it]})
    }

    private fun zeros(n: Int): Polynome{
        var result_coeffs = Array<Double>(n, {0.0})
        return Polynome(result_coeffs)
    }

    operator fun plus(other: Polynome): Polynome{
        var result = zeros(max(this.coeffs.size,other.coeffs.size))
        for(i in 0 until min(this.coeffs.size,other.coeffs.size)){
            result.coeffs[i] = this.coeffs[i] + other.coeffs[i]
        }
        for(i in min(this.coeffs.size,other.coeffs.size) until result.coeffs.size){
            result.coeffs[i] = if(this.coeffs.size > other.coeffs.size) this.coeffs[i] else other.coeffs[i]
        }
        result.clean()
        return result
    }
    operator fun times(other: Polynome): Polynome{
        var result = zeros(this.coeffs.size + other.coeffs.size)
        for(i in 0 until this.coeffs.size)
            for(j in 0 until other.coeffs.size)
                result.coeffs[i+j] += this.coeffs[i] * other.coeffs[j]
        result.clean()
        return result
    }
    operator fun times(d: Double): Polynome{
        var result = this
        for(i in 0 until this.coeffs.size)
            result.coeffs[i] *= d
        result.clean()
        return result
    }

    open fun evaluate(x: Double): Double{
        var result = 0.0
        for(i in 0 until this.coeffs.size)
            result += this.coeffs[i] * x.pow(i)
        return result
    }
    fun evaluate(x: Float) = evaluate(x.toDouble())
}