package de.iits.aoc

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun readInput(day: String) = readInput(day, "input.txt")
fun readTestInput(day: String) = readInput(day, "input_test.txt")
private fun readInput(day: String, filename: String) = Path("src/main/resources/$day/$filename").readLines()
