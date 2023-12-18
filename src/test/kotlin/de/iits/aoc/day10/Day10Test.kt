package de.iits.aoc.day10

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day10Test {

    @Nested
    inner class Part1 {

        @Test
        fun `with simple loop`() {
            val lines =
                """
                    .....
                    .S-7.
                    .|.|.
                    .L-J.
                    .....
                """.trimIndent().lines()

            Day10(lines).part1() shouldBe 4
        }

        @Test
        fun `simple loop with many not connected pipes`() {
            val lines =
                """
                    -L|F7
                    7S-7|
                    L|7||
                    -L-J|
                    L|-JF
                """.trimIndent().lines()

            Day10(lines).part1() shouldBe 4
        }

        @Test
        fun `more complex main loop with extra pipes`() {
            val lines =
                """
                    7-F7-
                    .FJ|7
                    SJLL7
                    |F--J
                    LJ.LJ
                """.trimIndent().lines()

            Day10(lines).part1() shouldBe 8
        }
    }

    @Nested
    @Disabled
    inner class Part2 {

        @Test
        fun `with simple loop`() {
            val lines =
                """
                    ...........
                    .S-------7.
                    .|F-----7|.
                    .||.....||.
                    .||.....||.
                    .|L-7.F-J|.
                    .|..|.|..|.
                    .L--J.L--J.
                    ...........
                """.trimIndent().lines()

            Day10(lines).part2() shouldBe 4
        }

        @Test
        fun `with larger loop`() {
            val lines =
                """
                    .F----7F7F7F7F-7....
                    .|F--7||||||||FJ....
                    .||.FJ||||||||L7....
                    FJL7L7LJLJ||LJ.L-7..
                    L--J.L7...LJS7F-7L7.
                    ....F-J..F7FJ|L7L7L7
                    ....L7.F7||L7|.L7L7|
                    .....|FJLJ|FJ|F7|.LJ
                    ....FJL-7.||.||||...
                    ....L---J.LJ.LJLJ...
                """.trimIndent().lines()

            Day10(lines).part2() shouldBe 8
        }

        @Test
        fun `complex loop`() {
            val lines =
                """
                    FF7FSF7F7F7F7F7F---7
                    L|LJ||||||||||||F--J
                    FL-7LJLJ||||||LJL-77
                    F--JF--7||LJLJIF7FJ-
                    L---JF-JLJIIIIFJLJJ7
                    |F|F-JF---7IIIL7L|7|
                    |FFJF7L7F-JF7IIL---7
                    7-L-JL7||F7|L7F-7F7|
                    L.L7LFJ|||||FJL7||LJ
                    L7JLJL-JLJLJL--JLJ.L
                """.trimIndent().lines()

            Day10(lines).part2() shouldBe 10
        }
    }
}
