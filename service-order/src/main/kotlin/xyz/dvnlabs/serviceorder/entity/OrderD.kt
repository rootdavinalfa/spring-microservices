package xyz.dvnlabs.serviceorder.entity

import javax.persistence.*

@Entity
@Table(name = "ORDERD")
data class OrderD(
    @EmbeddedId
    val id : OrderDPK? = null,
    val unitid : String? = null,
    val qty : Double = 0.0,
    val orderNote: String = "",
    var orderName : String = "",
    @Transient
    var orderFrom : String = ""
){
    /*@ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "order_id",updatable = false,insertable = false)
    private var orderH : OrderH? = null

    @PostLoad
    fun postLoad(){
        orderH?.let {
            this.orderFrom = it.orderFrom
        }
    }*/
}
