@file:Suppress("MatchingDeclarationName")

package de.iits.aoc.day01.seb03

import de.iits.aoc.readInput

class Day01(private val lines: List<String>) {

    private val words = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    private val revWords = words.map { it.reversed() }

    @Suppress("MagicNumber")
    fun part1() =
        lines.sumOf { line ->
            line.first { it.isDigit() }.digitToInt() * 10 + line.last { it.isDigit() }.digitToInt()
        }

    @Suppress("MagicNumber")
    fun part2() =
        lines.sumOf { line ->
            getNumber(line, words) * 10 + getNumber(line.reversed(), revWords)
        }

    @Suppress("MagicNumber")
    private val digits = List(10) { "$it" }

    private fun getNumber(line: String, words: List<String>): Int {
        val (wordIdx, word) = line.findAnyOf(words) ?: (Int.MAX_VALUE to "not found")
        val (digitIdx, digit) = line.findAnyOf(digits) ?: (Int.MAX_VALUE to "not found")

        return if (digitIdx < wordIdx) {
            digit.toInt()
        } else {
            word.digitWordToInt(words)
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
