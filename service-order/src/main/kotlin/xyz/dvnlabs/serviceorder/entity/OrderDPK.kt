package xyz.dvnlabs.serviceorder.entity

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
data class OrderDPK(
    val orderId : String,
    var orderDId : String
) : Serializable
