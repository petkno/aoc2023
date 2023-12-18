package de.iits.aoc.day18

import de.iits.aoc.Day
import de.iits.aoc.readInput
import kotlin.math.absoluteValue

class Day18(private val lines: List<String>) : Day() {

    override fun part1(): Int {
        var numberOfBoundaryPoints = 0
        val points = buildList<Pair<Int, Int>> {
            this.add(0 to 0)

            lines.map { line ->
                val (d, lengthAsString, _) = line.split(" ")
                val direction = DIRECTION.getValue(d)
                val length = lengthAsString.toInt()
                numberOfBoundaryPoints += length
                val lastPoint = this.last()
                this.add(lastPoint.first + direction.first * length to lastPoint.second + direction.second * length)
            }
        }

        val innerArea = List(points.size) { i ->
            val im1 = if (i == 0) points.lastIndex else i - 1
            val ip1 = if (i == points.lastIndex) 0 else i + 1
            points[i].first * (points[im1].second - points[ip1].second)
        }.sum().absoluteValue / 2
        val i = innerArea - numberOfBoundaryPoints / 2 + 1

        return i + numberOfBoundaryPoints
    }

    companion object {
        private val DIRECTION = mapOf(
            "U" to (-1 to 0),
            "D" to (1 to 0),
            "L" to (0 to -1),
            "R" to (0 to 1),
        )
    }
}

fun main() {
    val dayNumber = "18"
    val day = Day18(readInput("day$dayNumber"))

    println(day.part1())
    println(day.part2())
}
