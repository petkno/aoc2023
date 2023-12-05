package de.iits.aoc.day04.dmitry

import kotlin.io.path.Path
import kotlin.io.path.readLines

private const val DAY_ANSWER_1 = 28_750

fun main() {
    Path("src/main/resources/day04/input.txt").readLines()
        .map { line ->
            val (_, winningNumbersText, ourNumbersText) = line.split(":", "|")
            val winningNumbers = winningNumbersText.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
            val ourNumbers = ourNumbersText.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
            winningNumbers to ourNumbers
        }.map { (winningNumbers, ourNumbers) ->
            val count = winningNumbers.count { it in ourNumbers }
            if (count == 0) 0 else 1.shl(count - 1)
        }
        .sum()
        .also(::println)
        .let { check(it == DAY_ANSWER_1) }
}
