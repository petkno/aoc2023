package de.iits.aoc.day01.seb02

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

    @Suppress("MagicNumber")
    private val digits = List(10) { "$it" }

    private fun getNumber(line: String, startFrom: StartFrom): Int {
        val (wordIdx, word) = when (startFrom) {
            StartFrom.BEGINNING -> line.findAnyOf(words) ?: (Int.MAX_VALUE to "")
            StartFrom.END -> line.findLastAnyOf(words) ?: (Int.MIN_VALUE to "")
        }

        val (digitIdx, digit) = when (startFrom) {
            StartFrom.BEGINNING -> line.findAnyOf(digits) ?: (Int.MAX_VALUE to "")
            StartFrom.END -> line.findLastAnyOf(digits) ?: (Int.MIN_VALUE to "")
        }

        val shouldPickDigit = when (startFrom) {
            StartFrom.BEGINNING -> digitIdx < wordIdx
            StartFrom.END -> digitIdx > wordIdx
        }

        return if (shouldPickDigit) {
            digit.toInt()
        } else {
            word.digitWordToInt()
        }
    }

    private fun String.digitWordToInt(wordList: List<String> = words): Int {
        val index = wordList.indexOf(this)
        if (index == -1) error("$this is not a digit word!")
        return index + 1
    }
}

fun main() {
    val input = readInput("day01")
    println(Day01(input).part1())
    println(Day01(input).part2())
}
