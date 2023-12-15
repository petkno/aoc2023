package de.iits.aoc.day15

import de.iits.aoc.readInput

class Day15(private val input: List<String>) {
    private val steps = input.first().split(",")

    fun part1() = steps
        .sumOf { it.hash() }

    fun part2(): Int {
        @Suppress("MagicNumber")
        val boxes = List<MutableList<String>>(256) { mutableListOf<String>() }
        val focalLength = mutableMapOf<String, String>()
        steps.map { operation ->
            when {
                operation.endsWith("-") -> {
                    val label = operation.dropLast(1)
                    val index = label.hash()
                    if (boxes[index].contains(label)) {
                        boxes[index].remove(label)
                    }
                }

                else -> {
                    val (label, length) = operation.split("=")
                    val index = label.hash()
                    if (!boxes[index].contains(label)) {
                        boxes[index].add(label)
                    }

                    focalLength[label] = length
                }
            }
        }

        return boxes
            .mapIndexed { boxNumber, box ->
                box.mapIndexed { slotNumber, label ->
                    (boxNumber + 1) * (slotNumber + 1) * focalLength.getValue(label).toInt()
                }.sum()
            }.sum()
    }

    private fun String.hash(): Int {
        var hash = 0

        @Suppress("MagicNumber")
        this.map { c ->
            hash += c.code
            hash *= 17
            hash %= 256
        }

        return hash
    }
}

fun main() {
    val dayNumber = "15"
    val day = Day15(readInput("day$dayNumber"))

    println(day.part1())
    println(day.part2())
}
