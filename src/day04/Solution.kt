package day04

import readInput

data class Sections(val from: Int, val to: Int)

fun Sections.contains(other: Sections) = from <= other.from && other.to <= to

fun Sections.overlaps(other: Sections) =
    (other.from <= from && from <= other.to) || (other.from <= to && to <= other.to)

fun Sections(input: String): Sections {
    val (from, to) = input.split("-")
    return Sections(from.toInt(), to.toInt())
}

fun main() {
    fun part1(input: List<String>) =
        input.count {
            val (first, second) = it.split(",")
            val firstSection = Sections(first)
            val secondSection = Sections(second)
            firstSection.contains(secondSection) || secondSection.contains(firstSection)
        }

    fun part2(input: List<String>) =
        input.count {
            val (first, second) = it.split(",")
            val firstSection = Sections(first)
            val secondSection = Sections(second)
            firstSection.overlaps(secondSection) || secondSection.overlaps(firstSection)
        }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day04/data_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("day04/data")
    println(part1(input))
    println(part2(input))
}
