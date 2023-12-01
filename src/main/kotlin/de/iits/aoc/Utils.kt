package de.iits.aoc

import java.io.File

fun readInput(filename: String) = File("src/main/resources/$filename", "input.txt").readLines()
