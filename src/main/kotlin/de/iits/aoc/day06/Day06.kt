package de.iits.aoc.day06

import de.iits.aoc.readInput
import de.iits.aoc.readTestInput
import kotlin.system.measureTimeMillis

object Day06 {
    fun part1(input: List<String>) =
        input
            .map { line ->
                line.split(":")[1].split(" ").filter { it.isNotBlank() }.map { it.toLong() }
            }
            .let { calculateNumberOfPossibleBeats(it) }

    fun part2(input: List<String>) =
        input
            .map { line ->
                listOf(line.split(":")[1].replace(" ", "").toLong())
            }
            .let { calculateNumberOfPossibleBeats(it) }

    private fun calculateNumberOfPossibleBeats(races: List<List<Long>>): Long =
        races
            .let { it.first().zip(it.last()) { time, distance -> time to distance } }
            .map { (time, distance) ->
                (0..time)
                    .map { holdTime ->
                        holdTime * (time - holdTime)
                    }
                    .count { it > distance }
                    .toLong()
            }
            .reduce { acc, i -> acc * i }
}

private const val DAY_TEST_ANSWER_1 = 288L
private const val DAY_TEST_ANSWER_2 = 71_503L

fun main() {
    val dayNumber = "06"
    val day = Day06

    println("***** Day$dayNumber *****")
    val testInput = readTestInput("day$dayNumber")
    var part1Test: Long
    val part1TestMillis = measureTimeMillis {
        part1Test = day.part1(testInput)
    }
    check(part1Test == DAY_TEST_ANSWER_1)
    println("✅ one with $DAY_TEST_ANSWER_1 ✅ in $part1TestMillis ms")
    var part2Test: Long
    val part2TestMillis = measureTimeMillis {
        part2Test = day.part2(testInput)
    }
    check(part2Test == DAY_TEST_ANSWER_2)
    println("✅ two with $DAY_TEST_ANSWER_2 ✅ in $part2TestMillis ms")
    println("*****************")

    val input = readInput("day$dayNumber")
    var part1: Long
    val part1Millis = measureTimeMillis {
        part1 = day.part1(input)
    }
    println("🌟 one : $part1 in $part1Millis ms")
    var part2: Long
    val part2Millis = measureTimeMillis {
        part2 = day.part2(input)
    }
    println("🌟 two : $part2 in $part2Millis ms")
    println("*****************")
}
