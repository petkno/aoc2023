package de.iits.aoc

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun readInput(day: String) = readInput(day, "input.txt")
fun readTestInput(day: String) = readInput(day, "input_test.txt")
private fun readInput(day: String, filename: String) = Path("src/main/resources/$day/$filename").readLines()

fun transpose(input: List<String>): List<String> {
    if (input.isEmpty()) return input

    val transpose = Array<Array<Char>>(input.first().length) { Array<Char>(input.size) { '.' } }
    input.mapIndexed { i, row ->
        row.mapIndexed { j, c ->
            transpose[j][i] = c
        }
    }

    return transpose.map { it.joinToString(separator = "") }
}
