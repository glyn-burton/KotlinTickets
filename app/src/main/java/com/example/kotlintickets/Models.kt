package com.example.kotlintickets



data class Airline(val id: Int, val name: String, val logo: String)
data class Price(val price: Float, val seats: String, val currency: String,
                 val flightNumber: String,
                 val from: String, val to: String)

data class Ticket(val from: String, val to: String,
                  val departure: String, val arrival: String, val duration: String, val instructions: String,
                  val flightNumber: String,
                  val numberOfStops: Int,
                  val airline: Airline, var price: Price?
                  ) {
    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        return if (other !is Ticket) {
            false
        } else flightNumber.equals(other.flightNumber, ignoreCase = true)
    }

    override fun hashCode(): Int {
        var hash = 3
        hash = 53 * hash + flightNumber.hashCode()
        return hash
    }
}
