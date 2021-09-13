package xyz.dvnlabs.serviceorder.dto

import javax.validation.constraints.Email

data class CreateOrderDTO(
    val orderFrom : String = "",
    val contactNumber: String = "",
    @Email
    val email: String = "",
    val address: String = "",
    val orderList: List<CreateOrderList> = emptyList()
)
