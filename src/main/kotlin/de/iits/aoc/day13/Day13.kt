package de.iits.aoc.day13

import de.iits.aoc.Day
import de.iits.aoc.readInput
import de.iits.aoc.transpose

class Day13(input: List<String>) : Day() {

    private val blocks = buildList<List<String>> {
        var block = mutableListOf<String>()
        input.map { line ->
            when {
                line.isBlank() -> {
                    this.add(block)
                    block = mutableListOf<String>()
                }

                else -> block.add(line)
            }
        }
        this.add(block)
    }

    override fun part1() = blocks.sumOf { grid ->
        findMirror(grid) * ROW_MULTIPLIER + findMirror(transpose(grid))
    }

    private fun findMirror(grid: List<String>): Int {
        for (r in (1..grid.lastIndex)) {
            var above = grid.subList(0, r).reversed()
            var below = grid.subList(r, grid.size)

            above = above.subList(0, below.size.coerceAtMost(above.size))
            below = below.subList(0, above.size.coerceAtMost(below.size))

            if (above == below) {
                return r
            }
        }
        return 0
    }

    override fun part2() = blocks.map { grid ->
        findMirror2(grid) * ROW_MULTIPLIER + findMirror2(transpose(grid))
    }.sum()

    private fun findMirror2(grid: List<String>): Int {
        for (r in (1..grid.lastIndex)) {
            val above = grid.subList(0, r).reversed()
            val below = grid.subList(r, grid.size)

            val differences =
                above.zip(below)
                    .sumOf { (x, y) ->
                        x.zip(y).map { (a, b) ->
                            when {
                                a == b -> 0
                                else -> 1
                            }
                        }.sum()
                    }
            if (differences == 1) return r
        }
        return 0
    }

    companion object {
        private const val ROW_MULTIPLIER = 100
    }
}

fun main() {
    val dayNumber = "13"
    val day = Day13(readInput("day$dayNumber"))

    println(day.part1())
    println(day.part2())
}
