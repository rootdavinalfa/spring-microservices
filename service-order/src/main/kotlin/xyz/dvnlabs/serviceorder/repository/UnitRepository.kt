package xyz.dvnlabs.serviceorder.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import xyz.dvnlabs.serviceorder.entity.Unit

@Repository
interface UnitRepository : JpaRepository<Unit, String> {

    @Query(
        value = "SELECT MAX(unitid) FROM Unit"
    )
    fun maxUnitid(
    ): String?

}