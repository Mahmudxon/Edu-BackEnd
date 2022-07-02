package uz.mahmudxon.model

import org.jetbrains.exposed.sql.Table

data class Subject(
    val id: Int,
    val name: String,
    val teacherId: Int,
    val langId: Int
)

object Subjects : Table("subjects") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val teacherId = integer("teacher_id")
    val langId = integer("lang_id")
    override val primaryKey = PrimaryKey(id)
}