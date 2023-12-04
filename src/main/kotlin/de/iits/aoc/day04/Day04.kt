package de.iits.aoc.day04

import de.iits.aoc.readInput
import kotlin.math.pow

class Day04(private val cards: List<String>) {
    fun part1() =
        cards.map { card ->
            val numbers = getNumbers(card)
            val winningNumbers = numbers.first().trim().split(Regex("\\s+")).toSet()
            val ownNumbers = numbers.last().trim().split(Regex("\\s+")).toSet()

            ownNumbers.intersect(winningNumbers)
                .map(String::toInt)
        }
            .filter { it.isNotEmpty() }
            .sumOf { 2.0.pow(it.size - 1).toInt() }

    fun part2(): Int {
        val scratchcards = MutableList(cards.size) { 0 }
        cards.mapIndexed { i, card ->
            scratchcards[i] = scratchcards[i] + 1
            val numbers = getNumbers(card)
            val winningNumbers = numbers.first().trim().split(Regex("\\s+")).toSet()
            val ownNumbers = numbers.last().trim().split(Regex("\\s+")).toSet()

            val matchingNumbers = ownNumbers.intersect(winningNumbers).size
            for (winIndex in ((i + 1) until (i + 1 + matchingNumbers))) {
                scratchcards[winIndex] = scratchcards[winIndex] + scratchcards[i]
            }
        }

        return scratchcards.subList(0, cards.size).sum()
    }

    private fun getNumbers(card: String) = card.split(": ").last().split("|")
}

fun main() {
    val input = readInput("day04")
    println(Day04(input).part1())
    println(Day04(input).part2())
}
