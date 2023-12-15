package de.iits.aoc

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class UtilsTest {

    @Nested
    inner class Transpose {

        @Test
        fun `with empty list`() {
            transpose(emptyList<String>()) shouldBe emptyList<String>()
        }

        @Test
        fun `with only one string`() {
            val input = listOf("input")

            val result = transpose(input)

            result shouldBe listOf("i", "n", "p", "u", "t")
        }

        @Test
        fun `with multiple strings`() {
            val input = listOf("input", "outpu", "kaput")

            val result = transpose(input)

            result shouldBe listOf("iok", "nua", "ptp", "upu", "tut")
        }
    }
}
