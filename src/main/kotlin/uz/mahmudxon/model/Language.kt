package uz.mahmudxon.model

import org.jetbrains.exposed.sql.Table

data class Language(
    val id: Int,
    val name: String,
    val code: String
)

object Languages : Table("languages") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val code = varchar("code", 5)
    override val primaryKey = PrimaryKey(id)
}