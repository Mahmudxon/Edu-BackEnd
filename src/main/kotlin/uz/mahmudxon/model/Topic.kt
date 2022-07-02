package uz.mahmudxon.model

import org.jetbrains.exposed.sql.Table

data class Topic(
    var id: Int = 0,
    var title: String = "",
    var position: Int = 0,
)

object Topics : Table("topics") {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 255)
    val position = integer("position")
    override val primaryKey = PrimaryKey(id)
}