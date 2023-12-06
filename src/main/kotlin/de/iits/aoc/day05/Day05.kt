package de.iits.aoc.day05

import de.iits.aoc.readInput
import de.iits.aoc.readTestInput
import kotlin.system.measureTimeMillis

object Day05 {
    fun part1(input: List<String>): Long {
        val seedRanges = input
            .first().split(": ").last().split(Regex("\\s+"))
            .windowed(1, 1)
            .map { SeedRange(it.first(), "1") }

        val mappings = getMappings(input)

        return getLocationMinimum(seedRanges, mappings)
    }

    fun part2(input: List<String>): Long {
        val seedRanges = input
            .first().split(": ").last().split(Regex("\\s+"))
            .windowed(2, 2)
            .map { SeedRange(it.first(), it.last()) }

        val mappings = getMappings(input)

        return getLocationMinimum(seedRanges, mappings)
    }

    private const val START_OF_MAPPING_INDEX = 3

    private fun getMappings(input: List<String>) =
        buildList {
            var mapping = Mapping()
            for (i in START_OF_MAPPING_INDEX..input.lastIndex) {
                val line = input[i]
                when {
                    line.isBlank() -> continue
                    line.contains(":") -> {
                        this.add(mapping)
                        mapping = Mapping()
                    }

                    else -> {
                        mapping.add(Range(line.split(" ")))
                    }
                }
            }
            this.add(mapping)
        }

    private fun getLocationMinimum(seedRanges: List<SeedRange>, mappings: List<Mapping>): Long {
        var locationsMin = Long.MAX_VALUE
        seedRanges.forEach { seedRange ->
            for (seed in seedRange.getRange()) {
                var number = seed
                for (i in mappings.indices) {
                    number = mappings[i].map(number)
                }
                locationsMin = minOf(locationsMin, number)
            }
        }
        return locationsMin
    }
}

// region Data classes
data class Mapping(val ranges: MutableList<Range> = mutableListOf()) {
    fun add(range: Range) {
        ranges.add(range)
    }

    fun map(sourceNumber: Long): Long {
        ranges.forEach {
            if (it.isIn(sourceNumber)) {
                return it.map(sourceNumber)
            }
        }
        return sourceNumber
    }
}

data class Range(val sourceStart: Long, val destStart: Long, val length: Long) {
    fun isIn(sourceNumber: Long) = sourceNumber in LongRange(sourceStart, sourceStart + length - 1)
    fun map(sourceNumber: Long) =
        if (isIn(sourceNumber)) {
            sourceNumber + (destStart - sourceStart)
        } else {
            sourceNumber
        }
}

fun Range(mapping: List<String>): Range = Range(mapping[1].toLong(), mapping[0].toLong(), mapping[2].toLong())

data class SeedRange(val start: Long, val length: Long) {
    fun getRange() = LongRange(start, start + length - 1)
}

fun SeedRange(start: String, length: String): SeedRange = SeedRange(start.toLong(), length.toLong())
// endregion

private const val DAY_TEST_ANSWER_1 = 35L
private const val DAY_TEST_ANSWER_2 = 46L

fun main() {
    val dayNumber = "05"
    val day = Day05

    println("***** Day$dayNumber *****")
    val testInput = readTestInput("day$dayNumber")
    var part1Test: Long
    val part1TestMillis = measureTimeMillis {
        part1Test = day.part1(testInput)
    }
    check(part1Test == DAY_TEST_ANSWER_1)
    println("✅ one with $DAY_TEST_ANSWER_1 ✅ in $part1TestMillis ms")
    var part2Test: Long
    val part2TestMillis = measureTimeMillis {
        part2Test = day.part2(testInput)
    }
    check(part2Test == DAY_TEST_ANSWER_2)
    println("✅ two with $DAY_TEST_ANSWER_2 ✅ in $part2TestMillis ms")
    println("*****************")

    val input = readInput("day$dayNumber")
    var part1: Long
    val part1Millis = measureTimeMillis {
        part1 = day.part1(input)
    }
    println("🌟 one : $part1 in $part1Millis ms")
    var part2: Long
    val part2Millis = measureTimeMillis {
        part2 = day.part2(input)
    }
    println("🌟 two : $part2 in $part2Millis ms")
    println("*****************")
}
