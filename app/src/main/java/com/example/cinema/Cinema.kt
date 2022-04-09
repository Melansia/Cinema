package com.example.cinema


const val ROWS = 8
const val COLS = 7

fun main() {
    arrangement()
}

fun arrangement() {
    val array = Array(COLS) { CharArray(ROWS) { 'S' } }
    println("Cinema:")
    print(" ")
    for (i in 1..ROWS) print(" $i")
    println()
    array.forEachIndexed { index, chars -> println("${index + 1} ${chars.joinToString(" ")} ") }
}