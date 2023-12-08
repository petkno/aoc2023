package de.iits.aoc.day08

import de.iits.aoc.readInput

class Day08(input: List<String>) {
    private val lrInstructions = input.first()
    private val nodes = buildMap<String, Pair<String, String>> {
        input.drop(2).map { it.split(" = (", ", ", ")") }
            .map { (key, left, right) ->
                this[key] = left to right
            }
    }

    fun part1(): Int = calculateSteps("AAA")

    fun part2(): Long {
        val steps = nodes
            .map { it.key }
            .filter { it.endsWith('A') }
            .map { calculateSteps(it) }
            .map { it.toLong() }

        return findLCM(steps)
    }

    private fun calculateSteps(startElement: String): Int {
        var step = 1
        var currentElement = startElement
        while (true) {
            val instruction = lrInstructions[(step - 1) % lrInstructions.length]
            val nextElement = when (instruction) {
                'L' -> nodes.getValue(currentElement).first
                'R' -> nodes.getValue(currentElement).second
                else -> error("This should never happen")
            }

            if (nextElement.endsWith('Z')) break

            currentElement = nextElement
            step++
        }
        return step
    }

    private fun findLCM(a: Long, b: Long): Long {
        val larger = if (a > b) a else b
        val maxLcm = a * b
        var lcm = larger
        while (lcm <= maxLcm) {
            if (lcm % a == 0L && lcm % b == 0L) return lcm
            lcm += larger
        }
        return maxLcm
    }

    private fun findLCM(numbers: List<Long>): Long {
        var result = numbers.first()
        for (i in 1 until numbers.size) {
            result = findLCM(result, numbers[i])
        }
        return result
    }
}

fun main() {
    val dayNumber = "08"
    val day = Day08(readInput("day$dayNumber"))

    println(day.part1())
    println(day.part2())
}
