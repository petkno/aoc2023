package de.iits.aoc.day02

import de.iits.aoc.readInput
import kotlin.math.max

class Day02(input: List<String>) {
    private val games = input.map { it.toGame() }

    private fun String.toGame(): Game {
        val gameId = this.split(':').first().split(' ')[1].toInt()
        val hand = this.split(": ")
            .last()
            .split("; ")
            .map { cubeSet ->
                cubeSet
                    .split(", ")
                    .associate { cube ->
                        cube
                            .split(' ')
                            .let { (count, color) -> color to count.toInt() }
                    }
            }
            .also { println(it) }

        return Game(gameId = gameId, hand = hand)
    }

    fun part1(limits: Map<String, Int>) =
        games.sumOf {
            var count = it.gameId
            it.hand.forEach { hand ->
                limits.forEach { limit ->
                    if ((hand[limit.key] ?: 0) > limit.value) count = 0
                }
            }
            count
        }

    fun part2() =
        games.sumOf {
            val limits = mutableMapOf<String, Int>()
            it.hand.forEach { hand ->
                hand.forEach { color ->
                    limits[color.key] = max(limits[color.key] ?: 0, color.value)
                }
            }
            limits.values.reduce { acc, i -> acc * i }
        }

    data class Game(
        val gameId: Int,
        val hand: List<Map<String, Int>>
    )
}

fun main() {
    val input = readInput("day02")
    @Suppress("MagicNumber")
    println(Day02(input).part1(mapOf("red" to 12, "green" to 13, "blue" to 14)))
    println(Day02(input).part2())
}
