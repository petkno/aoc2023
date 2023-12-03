package de.iits.aoc.day03

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day03Test {
    private val input =
        """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
        """.trimIndent().lines()

    @Test
    fun part1() {
        Day03(input).part1() shouldBe 4361
    }

    @Test
    fun part2() {
        Day03(input).part2() shouldBe 467_835
    }
}
