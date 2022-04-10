package com.example.cinema


fun main() {
    println("Enter the number of rows:")
    val rows = 9//readln().toInt()
    println("Enter the number of seats in each row:")
    val seats = 9//readln().toInt()
    val cinema = MutableList(rows) { MutableList(seats) { 'S' } }

    displayCinema(cinema)

    println()
    displayCinema(stage(rows, seats, cinema))

}

fun displayCinema(list: MutableList<MutableList<Char>>) {
    val size = list[0].lastIndex + 1
    println()
    println("Cinema:")
    print(" ")
    for (i in 1..size) print(" $i")
    list.forEachIndexed { index, chars -> print("\n${index + 1} ${chars.joinToString(" ")} ") }
    println()
}

fun stage(rows: Int, seats: Int, list: MutableList<MutableList<Char>>): MutableList<MutableList<Char>> {
    val frontSeatPrice = 10
    val backSeatPrice = 8

    println("Enter a row number:")

    val rowsNR = readln().toInt() - 1

    println("Enter a seat number in that row:")

    val seatsNR = readln().toInt() - 1


    list[rowsNR - 1][seatsNR - 1] = 'B'

    val stage = rows * seats
//    var price: Int
//    if (rows % 2 == 0) {
//        price = (stage / 2) * backSeatPrice + (stage / 2) * frontSeatPrice
//    } else {
//        price = (rows / 2 * seats) * frontSeatPrice + (rows - rows / 2) * seats * backSeatPrice
//    }

    println()
    println()
    if (stage < 60) println("Ticket price: $$frontSeatPrice") //${stage * frontSeatPrice}
    if (stage > 60 && rowsNR > 4) println("Ticket price: $${backSeatPrice}") //${price}
    if (stage > 60 && rowsNR <= 4) println("Ticket price: $${frontSeatPrice}")

    return list
}