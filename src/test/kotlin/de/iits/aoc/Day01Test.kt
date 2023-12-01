package de.iits.aoc

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day01Test {

    private val day01 = Day01()

    @ParameterizedTest
    @MethodSource("validInputsPart1")
    fun `calibration value calculation - part1`(data: TestData) {
        day01.part1(data.calibrationDocument) shouldBe data.calibrationValue
    }

    @Suppress("UnusedPrivateMember")
    private fun validInputsPart1() = Stream.of(
        TestData(listOf("1abc2"), 12),
        TestData(listOf("pqr3stu8vwx"), 38),
        TestData(listOf("a1b2c3d4e5f"), 15),
        TestData(listOf("treb7uchet"), 77),
        TestData(listOf("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet"), 142),
    )

    @ParameterizedTest
    @MethodSource("validInputsPart2")
    fun `calibration value calculation - part2`(data: TestData) {
        day01.part2(data.calibrationDocument) shouldBe data.calibrationValue
    }

    @Suppress("UnusedPrivateMember")
    private fun validInputsPart2() = Stream.of(
        TestData(listOf("two1nine"), 29),
        TestData(listOf("eightwothree"), 83),
        TestData(listOf("abcone2threexyz"), 13),
        TestData(listOf("xtwone3four"), 24),
        TestData(listOf("4nineeightseven2"), 42),
        TestData(listOf("zoneight234"), 14),
        TestData(listOf("7pqrstsixteen"), 76),
        TestData(
            listOf(
                "two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen"
            ),
            281
        ),
        TestData(listOf("fivesixfive2six9hn"), 59),

        TestData(listOf("eighthree"), 83),
        TestData(listOf("sevenine"), 79),
    )

    data class TestData(
        val calibrationDocument: List<String>,
        val calibrationValue: Int
    )
}
