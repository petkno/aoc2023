package de.iits.aoc.day03

import de.iits.aoc.readInput

class Day03(private val lines: List<String>) {
    fun part1(): Int =
        getNumberPoses()
            .filter { isAdjacentToSymbol(it) }
            .sumOf { it.number() }

    fun part2(): Int {
        return getNumberPoses()
            .map { findGears(it) }
            .filter { it.isGearPart() }
            .groupBy { it.gear }
            .filterValues { it.size == 2 }
            .map { it.value }
            .sumOf {
                it.map(NumberPos::number).reduce { acc, i -> acc * i }
            }
    }

    private fun getNumberPoses(): MutableList<NumberPos> {
        val numbers = mutableListOf<NumberPos>()
        var numberPos: NumberPos? = null
        lines.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                if (c.isDigit()) {
                    numberPos = numberPos ?: NumberPos(y = y)
                    numberPos!!.addNumber(x, c)
                }
                if (!c.isDigit() || x == line.lastIndex) {
                    numberPos?.let {
                        numbers.add(numberPos!!)
                        numberPos = null
                    }
                }
            }
        }
        return numbers
    }

    private fun isAdjacentToSymbol(numberPos: NumberPos): Boolean {
        var symbols = ""
        for (y in ((numberPos.y - 1).coerceAtLeast(0)..(numberPos.y + 1).coerceAtMost(lines.size - 1))) {
            val line = lines[y]
            symbols += line.substring((numberPos.xStart - 1).coerceAtLeast(0), (numberPos.xEnd + 2).coerceAtMost(line.lastIndex))
        }
        symbols.forEach { c ->
            if (!c.isDigit() && c != '.' && !SYMBOLS.contains(c.toString())) {
                println(c)
            }
        }
        return symbols.findAnyOf(SYMBOLS) != null
    }

    private data class NumberPos(
        val y: Int,
        var xStart: Int = -1,
        var xEnd: Int = -1,
    ) {
        private var numberStr = ""
        var gear: Gear? = null

        fun addNumber(x: Int, c: Char) {
            if (xStart == -1) {
                xStart = x
            }
            xEnd = x
            numberStr += c
        }

        fun number() = numberStr.toInt()

        fun isGearPart() = gear != null

        override fun toString() = "NumberPos(y=$y, xStart=$xStart, xEnd=$xEnd, number=${number()}, gears=$gear)"
    }

    private fun findGears(numberPos: NumberPos): NumberPos {
        for (y in ((numberPos.y - 1).coerceAtLeast(0)..(numberPos.y + 1).coerceAtMost(lines.size - 1))) {
            val line = lines[y]
            for (x in ((numberPos.xStart - 1).coerceAtLeast(0)..(numberPos.xEnd + 1).coerceAtMost(line.lastIndex))) {
                if (line[x] == '*') {
                    numberPos.gear = Gear(x = x, y = y)
                }
            }
        }
        return numberPos
    }

    private data class Gear(val y: Int, val x: Int)

    companion object {
        private val SYMBOLS = listOf("*", "#", "$", "+", "&", "%", "=", "/", "-", "@")
    }
}

fun main() {
    val input = readInput("day03")
    println(Day03(input).part1())
    println(Day03(input).part2())
}
