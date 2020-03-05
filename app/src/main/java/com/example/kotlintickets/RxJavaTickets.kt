package com.example.kotlintickets

import io.reactivex.Observable

fun main()
{
    println("All Tickets:")
    ticketPrint()
    println("Tickets that are 500 and are Air Japan:")
    ticketFilter()
    println("Ticket Mapping:")
    ticketMap()
    Thread.sleep(3000)
}





fun ticketPrint()
{
    Observable.just(
        Ticket("NYC", "Montreal", "12:25pm", "4:55pm","4hrs30mins","Gate A","2407",0,
            Airline(123,"Air Canada","MAPLE"),
            Price(205f,"23","USD","2407","JFK","YMQ")),
        Ticket("Tokyo", "Osaka", "3:55pm", "5:25pm","1hrs30mins","Gate B","4766",0,
            Airline(345,"Air Japan","SUN"),
            Price(85f,"11","JPY","4766","TYO","OSA")),
        Ticket("Seattle", "Nevada", "8:10am", "12:25pm","4hrs15mins","Gate E","0186",1,
            Airline(567,"SouthWest","BIRD"),
            Price(310f,"5","USD","0186","SEA","LAS")),
        Ticket("Sapporo", "Seoul", "5:30pm", "8:40","3hrs10mins","Gate D","5667",1,
            Airline(345,"Air Japan","SUN"),
            Price(500f,"20","JPY","5667","SPK","SEL")))
        .subscribe{x->
            println(x)
        }
}

fun ticketFilter()
{
    Observable.just(
        Ticket("NYC", "Montreal", "12:25pm", "4:55pm","4hrs30mins","Gate A","2407",0,
            Airline(123,"Air Canada","MAPLE"),
            Price(205f,"23","USD","2407","JFK","YMQ")),
        Ticket("Tokyo", "Osaka", "3:55pm", "5:25pm","1hrs30mins","Gate B","4766",0,
            Airline(345,"Air Japan","SUN"),
            Price(85f,"11","JPY","4766","TYO","OSA")),
        Ticket("Seattle", "Nevada", "8:10am", "12:25pm","4hrs15mins","Gate E","0186",1,
            Airline(567,"SouthWest","BIRD"),
            Price(310f,"5","USD","0186","SEA","LAS")),
        Ticket("Sapporo", "Seoul", "5:30pm", "8:40","3hrs10mins","Gate D","5667",1,
            Airline(345,"Air Japan","SUN"),
            Price(500f,"20","JPY","5667","SPK","SEL")))
        .filter { ticket ->
            ticket.airline.name == "Air Japan"
        }
        .filter{ticket ->
            ticket.price?.price!! <= 1000f
        }
        .subscribe{ticket ->
            println(ticket)
        }
}

fun ticketMap() {
    val firstTicket = Ticket(
        "NYC", "Montreal", "12:25pm", "4:55pm", "4hrs30mins", "Gate A", "2407", 0,
        Airline(123, "Air Canada", "MAPLE"),
        null
    )

    val secondTicket = Ticket(
        "Tokyo", "Osaka", "3:55pm", "5:25pm", "1hrs30mins", "Gate B", "4766", 0,
        Airline(345, "Air Japan", "SUN"),
        null
    )

    val thirdTicket = Ticket(
        "Seattle", "Nevada", "8:10am", "12:25pm", "4hrs15mins", "Gate E", "0186", 1,
        Airline(567, "SouthWest", "BIRD"),
        null
    )
    val fourthTicket =Ticket(
        "Sapporo", "Seoul", "5:30pm", "8:40", "3hrs10mins", "Gate D", "5667", 1,
        Airline(345, "Air Japan", "SUN"),
        null)

    Observable.fromArray(arrayOf(firstTicket, secondTicket, thirdTicket, fourthTicket))

        .map {
            val randomPrice = randomPriceGenerator()
            val randomSeats = randomSeatsGenerator()
            val flightNumber = randomAirlineGenerator()
            val arrivalDeparture = randomCityGenerator()

            it.forEach {
                it.price = Price(randomPriceGenerator(), randomSeatsGenerator(), "JYP", randomAirlineGenerator(), randomCityGenerator(), randomCityGenerator())
            }
            it
        }
        .subscribe { x ->
            x.forEach {
                println("Tickets = $it")
            }
        }

}

fun randomSeatsGenerator():String
{

    val randomSeats = (5..33).random()

    return randomSeats.toString()

}

fun randomAirlineGenerator():String
{

    val randomAirline = (1000..5999).random()

    return randomAirline.toString()

}

fun randomCityGenerator():String
{

    val cityList = arrayOf("Tokyo","Osaka","Sapporo","Seoul", "Beijing","Kyoto")

    val randomCity = (0..cityList.size - 1).random()

    return cityList[randomCity]

}

fun randomPriceGenerator():Float
{
    val randomPrice = (85..1200).random()

    return randomPrice.toFloat()
}
