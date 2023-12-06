package de.iits.aoc.day03.maia

import de.iits.aoc.readInput
import de.iits.aoc.readTestInput
import kotlin.system.measureTimeMillis

typealias SchematicRow = List<Element>

object Day03 {
    const val EXPECTED_PART_1_TEST = 4_361L
    const val EXPECTED_PART_2_TEST = 467_835L

    fun part1(input: List<String>): Long {
        val engineSchematic: List<SchematicRow> = input.mapIndexed { i, s -> extractElements(s, i) }
        return engineSchematic
            .findParts()
            .sumOf { it.value }
            .toLong()
    }

    fun part2(input: List<String>): Long {
        val engineSchematic: List<SchematicRow> = input.mapIndexed { i, s -> extractElements(s, i) }
        return engineSchematic
            .flatten()
            .findGearParts()
            .sumOf { parts -> parts.first * parts.second }
            .toLong()
    }

    private fun extractElements(input: String, row: Int): List<Element> = buildList {
        var numberStart = -1
        var currentNumber = ""
        for ((index, c) in input.withIndex()) {
            when {
                c.isDigit() -> {
                    currentNumber += c
                    if (numberStart == -1) numberStart = index
                }

                else -> {
                    if (c != '.') this.add(Symbol(value = c, column = index, row = row))
                    if (currentNumber.isNotEmpty()) {
                        this.add(Number(number = currentNumber, start = numberStart, end = index - 1, row = row))
                        currentNumber = ""
                        numberStart = -1
                    }
                }
            }
        }
        if (currentNumber.isNotEmpty()) {
            this.add(Number(number = currentNumber, start = numberStart, end = input.length - 1, row = row))
        }
    }

    private fun List<SchematicRow>.findParts(): Set<Number> {
        val parts = mutableSetOf<Number>()
        this.windowed(2).map { twoRows ->
            val symbols: List<Symbol> = twoRows.flatten().filterIsInstance<Symbol>()
            val numbers: List<Number> = twoRows.flatten().filterIsInstance<Number>()
            numbers.filter { n ->
                symbols.any { s ->
                    s.column in n.expandedColumn
                }
            }.forEach { parts.add(it) }
        }
        return parts
    }

    private fun List<Element>.findGearParts(): List<Pair<Int, Int>> {
        val parts = this.filterIsInstance<Number>()
        val potentialGears = this.filterIsInstance<Symbol>().filter { it.value == '*' }
        return potentialGears
            .map { s ->
                // list of neighbours
                parts.filter { (s.row in it.expandedRow) && (s.column in it.expandedColumn) }
            }
            .filter { it.size == 2 }
            .map { it[0].value to it[1].value }
    }
}

// region Data classes
sealed class Element
data class Number(val value: Int, val xRange: IntRange, val row: Int) : Element() {
    val expandedColumn = xRange.first - 1..xRange.last + 1
    val expandedRow = row - 1..row + 1
}

fun Number(number: String, start: Int, end: Int, row: Int): Number = Number(number.toInt(), start..end, row)

data class Symbol(val value: Char, val column: Int, val row: Int) : Element()
// endregion

const val DAY_NUMBER = 3
val day = Day03
const val DAY_TEST_ANSWER_1 = day.EXPECTED_PART_1_TEST
const val DAY_TEST_ANSWER_2 = day.EXPECTED_PART_2_TEST

fun main() {
    println("***** Day$DAY_NUMBER *****")
    val testInput = readTestInput("day03")
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

    val input = readInput("day03")
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
