package xyz.dvnlabs.serviceorder.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.stereotype.Service
import xyz.dvnlabs.corelibrary.core.CommonHelper
import xyz.dvnlabs.corelibrary.exception.ResourceExistException
import xyz.dvnlabs.serviceorder.entity.Unit
import xyz.dvnlabs.serviceorder.repository.UnitRepository
import javax.transaction.Transactional

@Service
@Transactional(rollbackOn = [Exception::class])
class UnitService {
    @Autowired
    private lateinit var unitRepository: UnitRepository

    fun findById(id: String): Unit {

        return unitRepository.findById(id)
            .orElseThrow { throw Exception("Unit not found $id") }
    }

    fun saveUnit(unit: Unit): Unit {
        var lastSeq = unitRepository.maxUnitid()
            ?: kotlin.run { "0000" }
        lastSeq = CommonHelper.getStringSeq(lastSeq, null, "", "", 4, false, null)
        unit.unitid = lastSeq

        if (unitRepository.existsById(unit.unitid)) {
            throw ResourceExistException("Unit is exist with ${unit.unitid}")
        }


        return unitRepository.save(unit)
    }
}