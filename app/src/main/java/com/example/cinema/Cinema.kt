package com.example.cinema

const val FRONTSEATPRICE = 10
const val BACKSEATPRICE = 8

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seats = readln().toInt()
    val cinema = MutableList(rows) { MutableList(seats) { 'S' } }

    var currentIncome = 0
    var purchasedTickets = 0

    while (true) {
        when (menu()) {
            1 -> {
                displayCinema(cinema)
            }
            2 -> {
                val rowsSeat = gettingSeat(cinema)
                val ticketPrice =
                    if (cinema.size * cinema[0].size > 60 && rowsSeat[0] < cinema.size / 2 || cinema.size * cinema[0].size < 60) FRONTSEATPRICE else BACKSEATPRICE
                purchasedTickets++
                currentIncome += ticketPrice
                cinema[rowsSeat[0]][rowsSeat[1]] = 'B'
                println("Ticket price: $$ticketPrice")
            }
            3 -> {
                statistics(currentIncome, purchasedTickets, cinema)
            }
            0 -> break
        }
    }
}

fun menu(): Int {
    println()
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")
    return readln().toInt()
}

fun displayCinema(l: MutableList<MutableList<Char>>) {
    print("\nCinema:\n ")
    for (i in 1..l[0].size) print(if (i == l[0].size) " $i\n" else " $i")
    for (i in 1..l.size) println("$i ${l[i - 1].joinToString(" ")}")
}

fun gettingSeat(l: MutableList<MutableList<Char>>): MutableList<Int> {
    var switch = true

    var rowsNR = 0
    var seatNR = 0

    do {
        println("Enter a row number:")
        val rowsInp = readln().toInt()
        println("Enter a seat number in that row:")
        val seatInp = readln().toInt()

        if (rowsInp > l.size || seatInp > l[1].size) {
            println("\nWrong input!\n")
        } else if (l[rowsInp - 1][seatInp - 1] == 'B') {
            println("\nThat ticket has already been purchased!\n")
        } else if (rowsInp <= l.size && seatInp <= l[1].size) {
            rowsNR = rowsInp - 1
            seatNR = seatInp - 1
            switch = false
        }
    } while (switch)
    return mutableListOf(rowsNR, seatNR)
}

fun statistics(currentIncome: Int, purchasedTickets: Int, l: MutableList<MutableList<Char>>) {
    println()

    val hallSize = l.size * l[0].size
    val totalIncome = if (l.size * l[0].size <= 60) {
        l.size * l[0].size * FRONTSEATPRICE
    } else {
        ((l.size / 2 * l[0].size) * FRONTSEATPRICE) + (l.size - l.size / 2) * l[0].size * BACKSEATPRICE
    }

    val percentage = purchasedTickets.toDouble() / hallSize.toDouble() * 100.0
    val formatPercentage = "%.2f".format(percentage)

    val stat = """
        Number of purchased tickets: $purchasedTickets
        Percentage: $formatPercentage%
        Current income: $${currentIncome}
        Total income: $${totalIncome}
        """.trimIndent()
    print(stat)
    println()
}