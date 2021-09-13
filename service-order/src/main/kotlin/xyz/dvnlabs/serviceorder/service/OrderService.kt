package xyz.dvnlabs.serviceorder.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xyz.dvnlabs.corelibrary.core.CommonHelper
import xyz.dvnlabs.corelibrary.exception.ResourceExistException
import xyz.dvnlabs.serviceorder.dto.CreateOrderDTO
import xyz.dvnlabs.serviceorder.entity.OrderD
import xyz.dvnlabs.serviceorder.entity.OrderDPK
import xyz.dvnlabs.serviceorder.entity.OrderH
import xyz.dvnlabs.serviceorder.repository.OrderDRepository
import xyz.dvnlabs.serviceorder.repository.OrderRepository
import xyz.dvnlabs.serviceorder.repository.UnitRepository
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional(rollbackOn = [Exception::class])
class OrderService {
    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Autowired
    private lateinit var orderDRepository: OrderDRepository

    @Autowired
    private lateinit var unitService: UnitService


    private fun saveOrderH(orderH: OrderH): OrderH {
        var lastSeq = orderRepository.maxOrderId(Date())
            ?: kotlin.run { "YYMMDD00000" }

        lastSeq = CommonHelper.getStringSeq(lastSeq, Date(), "", "", 5, false, "yyMMdd")
        println(lastSeq)
        orderH.orderId = lastSeq
        if (orderRepository.existsById(orderH.orderId)) {
            throw ResourceExistException("ORDERH is exist")
        }

        return orderRepository.save(orderH)
    }

    private fun saveOrderD(orderD: OrderD): OrderD {
        var lastSeq = orderDRepository.maxIDOrderD(orderD.id!!.orderId)
            ?: kotlin.run { "0000" }
        lastSeq = CommonHelper.getStringSeq(lastSeq, null, "", "", 4, false, null)
        orderD.id.orderDId = lastSeq
        if (orderDRepository.existsById(orderD.id)) {
            throw ResourceExistException("ORDERD is exist")
        }
        return orderDRepository.saveAndFlush(orderD)
    }

    fun createNewOrder(createOrderDTO: CreateOrderDTO) {

        val orderH = saveOrderH(
            OrderH(
                orderFrom = createOrderDTO.orderFrom,
                contactNumber = createOrderDTO.contactNumber,
                email = createOrderDTO.email,
                address = createOrderDTO.address
            )
        )
        createOrderDTO.orderList.forEach {
            val unit = unitService.findById(it.unitid)
            if (unit.unitqty == 0.0) {
                throw Exception("Can't order, product name ${unit.unitname} currently not available")
            }
            saveOrderD(
                OrderD(
                    OrderDPK(orderH.orderId, ""), it.unitid, it.qty, it.orderNote, it.orderName
                )
            )
        }
    }

    fun findAllOrder(): List<OrderH> {
        return orderRepository.findAll()
    }

}