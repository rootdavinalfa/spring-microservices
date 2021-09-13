package xyz.dvnlabs.serviceorder.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "UNIT")
data class Unit(
    @Id var unitid : String,
    var unitname : String,
    var unitqty : Double = 0.0
)
