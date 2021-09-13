package xyz.dvnlabs.serviceorder.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import xyz.dvnlabs.serviceorder.dto.CreateOrderDTO
import xyz.dvnlabs.serviceorder.entity.OrderH
import xyz.dvnlabs.serviceorder.service.OrderService

@RequestMapping("/order")
@RestController
class OrderController {
    @Autowired
    private lateinit var orderService: OrderService

    @PostMapping("/create")
    fun createOrder(
        @RequestBody createOrderDTO: CreateOrderDTO
    ) {
        orderService.createNewOrder(createOrderDTO)
    }

    @GetMapping("/list-orderh")
    fun listOrderH(): List<OrderH> {
        return orderService.findAllOrder()
    }


}