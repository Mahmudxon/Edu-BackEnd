package uz.mahmudxon.model

import org.jetbrains.exposed.sql.Table

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String?,
    val phone: String?,
    val password: String,
    val imgUrl: String?,
    val role: Int
) {
    object Role {
        const val ADMIN = 1
        const val USER = 2
    }
}

object Users : Table("users") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val username = varchar("username", 255)
    val email = varchar("email", 255)
    val phone = varchar("phone", 255)
    val password = varchar("password", 255)
    val imgUrl = varchar("imgUrl", 255)
    val role = integer("role")

    override val primaryKey = PrimaryKey(id)
}