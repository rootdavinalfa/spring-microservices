package xyz.dvnlabs.serviceorder.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import xyz.dvnlabs.serviceorder.entity.OrderD
import xyz.dvnlabs.serviceorder.entity.OrderDPK
import java.util.*

interface OrderDRepository : JpaRepository<OrderD,OrderDPK>,JpaSpecificationExecutor<OrderD> {

    @Query(value = "SELECT max(id.orderDId) FROM OrderD " +
            "WHERE id.orderId = :orderId ")
    fun maxIDOrderD(
        @Param("orderId") orderId : String
    ) : String?

}