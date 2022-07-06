package uz.mahmudxon.model

import org.jetbrains.exposed.sql.Table

data class Device(
    val id: Int,
    val os: String,
    val osVersion: String,
    val manufacturer: String,
    val model: String,
    val type: String,
)

object Devices : Table("devices") {
    val id = integer("id").autoIncrement()
    val os = varchar("os", 50)
    val osVersion = varchar("os_version", 50)
    val manufacturer = varchar("manufacturer", 50)
    val model = varchar("model", 50)
    val type = varchar("type", 50)
    override val primaryKey = PrimaryKey(id)
}