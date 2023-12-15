package de.iits.aoc.day13

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day13Test {

    private val day = Day13(
        """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.
            
            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#
        """.trimIndent().lines()
    )

    @Test
    fun part1() {
        day.part1() shouldBe 405
    }

    @Test
    fun part2() {
        day.part2() shouldBe 400
    }
}
