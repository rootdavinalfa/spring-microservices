package xyz.dvnlabs.serviceorder.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import xyz.dvnlabs.serviceorder.entity.OrderH
import java.util.*

@Repository
interface OrderRepository : JpaRepository<OrderH, String> {

    @Query(
        value = "SELECT max (orderId) FROM OrderH " +
                "WHERE orderDate = :orderDate "
    )
    fun maxOrderId(
        @Param("orderDate") orderDate: Date
    ) : String?
}