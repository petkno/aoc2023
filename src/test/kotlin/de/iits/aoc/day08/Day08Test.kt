package de.iits.aoc.day08

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day08Test {

    @Test
    fun part1_1() {
        val input =
            """
                RL

                AAA = (BBB, CCC)
                BBB = (DDD, EEE)
                CCC = (ZZZ, GGG)
                DDD = (DDD, DDD)
                EEE = (EEE, EEE)
                GGG = (GGG, GGG)
                ZZZ = (ZZZ, ZZZ)
            """.trimIndent().lines()

        Day08(input).part1() shouldBe 2
    }

    @Test
    fun part1_2() {
        val input =
            """
                LLR
                
                AAA = (BBB, BBB)
                BBB = (AAA, ZZZ)
                ZZZ = (ZZZ, ZZZ)
            """.trimIndent().lines()

        Day08(input).part1() shouldBe 6
    }

    @Test
    fun part2() {
        val input =
            """
                LR
                
                11A = (11B, XXX)
                11B = (XXX, 11Z)
                11Z = (11B, XXX)
                22A = (22B, XXX)
                22B = (22C, 22C)
                22C = (22Z, 22Z)
                22Z = (22B, 22B)
                XXX = (XXX, XXX)
            """.trimIndent().lines()

        Day08(input).part2() shouldBe 6
    }
}
