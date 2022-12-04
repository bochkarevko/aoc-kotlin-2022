package day03

import readInput

val Char.priority: Int
    get() = when {
        isLowerCase() -> code - 'a'.code + 1
        else -> code - 'A'.code + 27
    }

fun main() {
    fun part1(input: List<String>) =
        input.sumOf {
            val comp1 = it.substring(0, it.length / 2)
            val comp2 = it.substring(it.length / 2)
            val shared = comp1.toSet().intersect(comp2.toSet())
            shared.firstOrNull()?.priority ?: 0
        }

    fun part2(input: List<String>) = input.chunked(3).sumOf {
        val (elf1, elf2, elf3) = it
        elf1.toSet().intersect(elf2.toSet()).intersect(elf3.toSet()).firstOrNull()?.priority ?: 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day03/data_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("day03/data")
    println(part1(input))
    println(part2(input))
}
