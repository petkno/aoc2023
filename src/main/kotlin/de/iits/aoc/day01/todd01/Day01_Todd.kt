@file:Suppress("MatchingDeclarationName", "Filename")

package de.iits.aoc.day01.todd01

import de.iits.aoc.readInput

class Day01(private val input: List<String>) {

    @Suppress("MagicNumber")
    private val words = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    fun part1() =
        input.sumOf { calibrationValue(it) }

    fun part2() =
        input.sumOf { row ->
            calibrationValue(
                // Run through each character and turn it into a digit or a null,
                // and then map each of them to a String. In theory, we could take
                // the first and last digits from the resulting list instead of joining.
                row.mapIndexedNotNull { index, c ->
                    // If it is a digit, take it as-is
                    if (c.isDigit()) {
                        c
                    } else {
                        // Otherwise, see if this is the start of a word and if so map to the
                        // digit that is represents.
                        row.possibleWordsAt(index).firstNotNullOfOrNull { candidate ->
                            words[candidate]
                        }
                    }
                }.joinToString()
            )
        }

    private fun calibrationValue(row: String): Int {
        val firstDigit = row.first { it.isDigit() }
        val lastDigit = row.last { it.isDigit() }
        return "$firstDigit$lastDigit".toInt()
    }

    @Suppress("MagicNumber")
    private fun String.possibleWordsAt(startingAt: Int) =
        (3..5).map { len ->
            substring(startingAt, (startingAt + len).coerceAtMost(length))
        }
}

fun main() {
    val input = readInput("day01")
    println(Day01(input).part1())
    println(Day01(input).part2())
}
