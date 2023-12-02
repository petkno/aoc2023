package de.iits.aoc

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun readInput(filename: String) = Path("src/main/resources/$filename/input.txt").readLines()
