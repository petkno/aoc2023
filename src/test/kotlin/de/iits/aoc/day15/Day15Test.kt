package de.iits.aoc.day15

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day15Test {

    @Test
    fun part1() {
        val input =
            """
                rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7
            """.trimIndent().lines()

        Day15(input).part1() shouldBe 1320
    }

    @Test
    fun part2() {
        val input =
            """
                rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7
            """.trimIndent().lines()

        Day15(input).part2() shouldBe 145
    }
}
