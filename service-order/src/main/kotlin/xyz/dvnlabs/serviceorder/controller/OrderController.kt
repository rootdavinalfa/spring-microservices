package xyz.dvnlabs.serviceorder.controller

import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import xyz.dvnlabs.serviceorder.dto.CreateOrderDTO
import xyz.dvnlabs.serviceorder.entity.OrderD
import xyz.dvnlabs.serviceorder.entity.OrderH
import xyz.dvnlabs.serviceorder.service.OrderService

@RequestMapping("/order")
@RestController
class OrderController {
    @Autowired
    private lateinit var orderService: OrderService

    @PostMapping("/create")
    @ApiOperation("Create Order")
    fun createOrder(
        @RequestBody createOrderDTO: CreateOrderDTO
    ) {
        orderService.createNewOrder(createOrderDTO)
    }

    @GetMapping("/list-orderh")
    @ApiOperation("List Order")
    fun listOrderH(): List<OrderH> {
        return orderService.findAllOrder()
    }

    @GetMapping("/list-orderd")
    @ApiOperation("List Orderd")
    fun listOrderD(
    ): List<OrderD> {
        return orderService.findAllOrderD()
    }


}