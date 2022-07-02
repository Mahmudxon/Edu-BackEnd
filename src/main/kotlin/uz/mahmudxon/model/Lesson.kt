package uz.mahmudxon.model

import org.jetbrains.exposed.sql.Table

data class Lesson(
    var id: Int = 0,
    var title: String = "",
    var subject_id: Int = 0,
    var position: Int = 0,
)

object Lessons : Table("lessons") {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 255)
    val subject_id = integer("subject_id")
    val position = integer("position")
    override val primaryKey = PrimaryKey(id)
}
