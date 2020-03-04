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
            ticket.price?.price == 500f
        }
        .subscribe{ticket ->
            println(ticket)
        }
}

fun ticketMap()
{
    val tickets1 = Observable.just(
        Ticket("NYC", "Montreal", "12:25pm", "4:55pm","4hrs30mins","Gate A","2407",0,
            Airline(123,"Air Canada","MAPLE"),
            Price(205f,"23","USD","2407","JFK","YMQ")),
        Ticket("Tokyo", "Osaka", "3:55pm", "5:25pm","1hrs30mins","Gate B","4766",0,
            Airline(345,"Air Japan","SUN"),
            Price(85f,"11","JPY","4766","TYO","OSA")))
    val tickets2 = Observable.just(
        Ticket("Seattle", "Nevada", "8:10am", "12:25pm","4hrs15mins","Gate E","0186",1,
        Airline(567,"SouthWest","BIRD"),
        Price(310f,"5","USD","0186","SEA","LAS")),
        Ticket("Sapporo", "Seoul", "5:30pm", "8:40","3hrs10mins","Gate D","5667",1,
            Airline(345,"Air Japan","SUN"),
            Price(500f,"20","JPY","5667","SPK","SEL")))

    Observable.concat(tickets1, tickets2)
        .filter{ticket ->
            ticket.airline.name == "Air Canada"
        }
        .subscribe{ticket ->
            println(ticket)
        }
}