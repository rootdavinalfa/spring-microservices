package xyz.dvnlabs.serviceorder.core.init

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import xyz.dvnlabs.serviceorder.entity.Unit
import xyz.dvnlabs.serviceorder.service.UnitService

@Component
class DataInitialization : ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private lateinit var unitService: UnitService

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        createUnit()
    }

    fun createUnit() {
        val units = listOf(
            Unit(
                unitname = "Laptop",
                unitqty = 100.0
            ),
            Unit(
                unitname = "HP",
                unitqty = 56.0
            ),
            Unit(
                unitname = "Tablet",
                unitqty = 14.0
            ), Unit(
                unitname = "Mouse",
                unitqty = 22.0
            ),
        )
        units.forEach {
            unitService.saveUnit(it)
        }

    }

}