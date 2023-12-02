package de.iits.aoc.day01

import de.iits.aoc.readInput

class Day01 {
    fun part1(input: List<String>) = input
        .sumOf { calculateCalibrationValue(it) }

    private fun calculateCalibrationValue(calibrationLine: String): Int {
        val digits = getDigits(calibrationLine)
        return "${digits.first().digitToInt()}${digits.last().digitToInt()}".toInt()
    }

    private fun getDigits(calibrationLine: String) = calibrationLine.toCharArray().filter { it.isDigit() }

    fun part2(input: List<String>) = input
        .map { convertSpelledToDigit(it) }
        .sumOf { calculateCalibrationValue(it) }

    private fun convertSpelledToDigit(calibrationLine: String): String {
        var result = ""
        var i = 0
        while (i < calibrationLine.length) {
            var replacement: String? = null
            for ((spelledIndex, spelled) in SPELLED_DIGITS) {
                if (calibrationLine.startsWith(spelled, i)) {
                    replacement = "${spelledIndex + 1}"
                    break
                }
            }
            result += replacement?.let { replacement } ?: calibrationLine[i]
            i++
        }
        return result
    }

    companion object {
        val SPELLED_DIGITS = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine").withIndex()
    }
}

fun main() {
    val input = readInput("day01")
    println(Day01().part1(input))
    println(Day01().part2(input))
}
