package uz.mahmudxon.model

import org.jetbrains.exposed.sql.Table

data class User(
    var id: Int = 0,
    val name: String,
    val username: String,
    val email: String? = null,
    val phone: String,
    val password: String,
    val imgUrl: String? = null,
    val role: Int = Role.STUDENT
) {
    object Role {
        const val ADMIN = 1
        const val STUDENT = 2
        const val TEACHER = 3
        const val SUPER_ADMIN = 777
    }
}


object Users : Table("users") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val username = varchar("username", 255).uniqueIndex()
    val email = varchar("email", 255).nullable()
    val phone = varchar("phone", 255).uniqueIndex()
    val password = varchar("password", 255)
    val imgUrl = varchar("imgUrl", 255).nullable()
    val role = integer("role")

    override val primaryKey = PrimaryKey(id)
}