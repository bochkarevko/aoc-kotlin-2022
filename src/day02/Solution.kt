package day02

import readInput

enum class Choice {
    ROCK {
        override val score: Int = 1
    },
    PAPER {
        override val score: Int = 2
    },
    SCISSORS {
        override val score: Int = 3
    },
    ;

    abstract val score: Int
}

val Choice.beats: Choice
    get() = when(this) {
        Choice.ROCK -> Choice.SCISSORS
        Choice.SCISSORS -> Choice.PAPER
        Choice.PAPER -> Choice.ROCK
    }

val Choice.loses: Choice
    get() = when(this) {
        Choice.ROCK -> Choice.PAPER
        Choice.SCISSORS -> Choice.ROCK
        Choice.PAPER -> Choice.SCISSORS
    }

fun roundScore(opponent: Choice, me: Choice): Int {
    if (opponent == me) {
        return 3
    }
    if (me.beats == opponent) {
        return 6
    }
    return 0
}

fun String.toChoice() = when(this) {
    "A", "X" -> Choice.ROCK
    "B", "Y" -> Choice.PAPER
    "C", "Z" -> Choice.SCISSORS
    else -> throw IllegalArgumentException("Forbidden choice")
}

fun List<String>.toChoices() = flatMap { it.split(" ") }.map { it.toChoice() }

fun List<String>.toChoicesWithOutcomes() = flatMap { it.split(" ") }.chunked(2).flatMap {
    val (opponentStr, outcomeStr) = it
    val opponent = opponentStr.toChoice()
    val me = when(outcomeStr) {
        "X" -> opponent.beats
        "Y" -> opponent
        "Z" -> opponent.loses
        else -> throw IllegalArgumentException("Forbidden choice")
    }
    listOf(opponent, me)
}

fun List<Choice>.gamePoints() = chunked(2).sumOf {
    val (opponent, me) = it
    me.score + roundScore(opponent, me)
}

fun main() {
    fun part1(input: List<String>) = input.toChoices().gamePoints()

    fun part2(input: List<String>): Int = input.toChoicesWithOutcomes().gamePoints()

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day02/data_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("day02/data")
    println(part1(input))
    println(part2(input))
}
