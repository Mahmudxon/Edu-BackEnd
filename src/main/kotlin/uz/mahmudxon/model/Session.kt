package uz.mahmudxon.model

import org.jetbrains.exposed.sql.Table

data class Session(
    val id: Int,
    val token: String,
    val userId: Int,
    val createdAt: Long,
    val deviceId: Int,
    val deletedAt: Long? = null,
    val isDeleted: Boolean = false
)

object Sessions : Table("sessions") {
    val id = integer("id").autoIncrement()
    val token = varchar("token", 255)
    val userId = integer("user_id")
    val createdAt = long("created_at")
    val deviceId = integer("device_id")
    val deletedAt = long("deleted_at").nullable()
    val isDeleted = bool("is_deleted").default(false)
    override val primaryKey: PrimaryKey = PrimaryKey(id)
}