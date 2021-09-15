package xyz.dvnlabs.serviceorder.controller

import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.dvnlabs.serviceorder.entity.Unit
import xyz.dvnlabs.serviceorder.service.UnitService

@RequestMapping("/unit")
@RestController
class UnitController {

    @Autowired
    private lateinit var unitService: UnitService

    @GetMapping("/list-unit")
    @ApiOperation("List Unit")
    fun listOrderH(): List<Unit> {
        return unitService.findAll()
    }
}