package xyz.dvnlabs.serviceorder.entity

import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email

@Entity
@Table(name = "ORDERH")
data class OrderH(
    @Id
    var orderId: String = "",
    @Temporal(TemporalType.DATE)
    val orderDate: Date = Date(),
    val orderFrom: String = "",
    val contactNumber: String = "",
    @Email
    val email: String = "",
    val address: String = ""
)
