package de.iits.aoc.day01.seb01

import de.iits.aoc.readInput

class Day01(private val lines: List<String>) {

    private val words = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    @Suppress("MagicNumber")
    fun part1() =
        lines.sumOf { line ->
            line.first { it.isDigit() }.digitToInt() * 10 + line.last { it.isDigit() }.digitToInt()
        }

    @Suppress("MagicNumber")
    fun part2() =
        lines.sumOf { line ->
            getNumber(line, StartFrom.BEGINNING) * 10 + getNumber(line, StartFrom.END)
        }

    enum class StartFrom { BEGINNING, END }

    private fun getNumber(line: String, startFrom: StartFrom): Int {
        val indices = when (startFrom) {
            StartFrom.BEGINNING -> line.indices
            StartFrom.END -> line.lastIndex downTo 0
        }
        for (idx in indices) {
            line[idx].digitToIntOrNull()?.let { return it }
            for ((wordIndex, word) in words.withIndex()) {
                if (line.substring(idx).startsWith((word))) {
                    return (wordIndex + 1)
                }
            }
        }
        error("Unreachable")
    }
}

fun main() {
    val input = readInput("day01")
    println(Day01(input).part1())
    println(Day01(input).part2())
}
