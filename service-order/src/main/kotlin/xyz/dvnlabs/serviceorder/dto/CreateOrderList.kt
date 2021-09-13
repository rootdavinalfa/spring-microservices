package xyz.dvnlabs.serviceorder.dto

data class CreateOrderList(
    val orderName : String = "",
    val unitid : String = "",
    val qty : Double = 0.0,
    val orderNote : String = ""
)
