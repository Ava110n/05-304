package org.example.project

class Lagrange: Polynome {
    var points: List<Pair<Double, Double>>

    /*constructor(points: List<Pair<Double, Double>>){
        this.points = points
    }*/
    constructor(cartesians: List<Cartesian>){
        var points = mutableListOf<Pair<Double, Double>>()
        for(c in cartesians){
            points.add(Pair(c.x.toDouble(), c.y.toDouble()))
        }
        this.points = points
    }

    private fun basis(i: Int): Polynome{
        var l = Polynome(arrayOf(1.0))
        for(j in 0 until points.size)
            if(j!=i)
                l *= Polynome(arrayOf(
                    this.points[j].first / (this.points[j].first - this.points[i].first),
                    1 / (this.points[i].first - this.points[j].first)
                ))
        return l
    }

    private fun lagrange_polynome(): Polynome{
        var result = Polynome(arrayOf(0.0))
        for(i in 0 until points.size)
            result += basis(i) * points[i].second
        return result
    }
    override fun evaluate(x: Double): Double{
        var p = lagrange_polynome()
        return p.evaluate(x)
    }

}