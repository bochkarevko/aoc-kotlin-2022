package day01

import readInput

data class Elf(val i: Int, val calories: Int)

fun Elf(i: Int, items: List<String>) = Elf(i, items.sumOf { it.toInt() })

fun main() {
    fun sortedElves(input: List<String>): List<Elf> {
        val elves: MutableList<Elf> = mutableListOf()
        var from = 0
        while (from < input.size) {
            val to = input.subList(from, input.size).indexOfFirst { it.isEmpty() }
            if (to == -1) break
            elves.add(Elf(elves.size + 1, input.subList(from, from + to)))
            from += to + 1
        }
        return elves.sortedByDescending { it.calories }
    }

    fun part1(elves: List<Elf>): Int = elves.first().calories

    fun part2(elves: List<Elf>): Int = elves.take(3).sumOf { it.calories }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day01/data_test")
    val testElves = sortedElves(testInput)
    check(part1(testElves) == 24000)
    check(part2(testElves) == 45000)

    val input = readInput("day01/data")
    val elves = sortedElves(input)
    println(part1(elves))
    println(part2(elves))
}
