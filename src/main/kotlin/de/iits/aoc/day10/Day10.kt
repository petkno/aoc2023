package de.iits.aoc.day10

import de.iits.aoc.Day
import de.iits.aoc.readInput
import kotlin.math.absoluteValue

class Day10(private val lines: List<String>) : Day() {

    override fun part1(): Int {
        val start = getStart()
        val loop = getLoop(start)

        return loop.size / 2
    }

    override fun part2(): Int {
        val start = getStart()
        val loop = getLoop(start)

        val innerArea = List(loop.size) { i ->
            val im1 = if (i == 0) loop.lastIndex else i - 1
            val ip1 = if (i == loop.lastIndex) 0 else i + 1
            loop[i].first * (loop[im1].second - loop[ip1].second)
        }.sum().absoluteValue / 2

        println("innerArea: $innerArea")

        val i = innerArea - loop.size / 2 + 1

        return i
    }

    private fun getStart(): Pair<Int, Int> {
        lines.forEachIndexed { r, row ->
            row.forEachIndexed { c, ch ->
                if (ch == 'S') return r to c
            }
        }
        error("no start point ('S') found")
    }

    @Suppress("CyclomaticComplexMethod")
    private fun getLoop(start: Pair<Int, Int>): List<Pair<Int, Int>> {
        val loop = mutableListOf(start)
        val queue = mutableListOf(start)

        while (queue.isNotEmpty()) {
            val point = queue.removeFirst()
            val (row, col) = point
            val ch = lines[row][col]

            @Suppress("ComplexCondition")
            if (row > 0 && "S|JL".contains(ch) && "|7F".contains(lines[row - 1][col]) && !loop.contains(row - 1 to col)) {
                loop.add(row - 1 to col)
                queue.add(row - 1 to col)
            }

            @Suppress("ComplexCondition")
            if (row < lines.lastIndex && "S|7F".contains(ch) && "|JL".contains(lines[row + 1][col]) && !loop.contains(row + 1 to col)) {
                loop.add(row + 1 to col)
                queue.add(row + 1 to col)
            }

            @Suppress("ComplexCondition")
            if (col > 0 && "S-J7".contains(ch) && "-LF".contains(lines[row][col - 1]) && !loop.contains(row to col - 1)) {
                loop.add(row to col - 1)
                queue.add(row to col - 1)
            }

            @Suppress("ComplexCondition")
            if (col < lines[row].lastIndex && "S-LF".contains(ch) && "-J7".contains(lines[row][col + 1]) && !loop.contains(row to col + 1)) {
                loop.add(row to col + 1)
                queue.add(row to col + 1)
            }
        }

        return loop
    }
}

fun main() {
    val dayNumber = "10"
    val day = Day10(readInput("day$dayNumber"))

    println(day.part1())
    println(day.part2())
}
