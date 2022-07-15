package uz.mahmudxon.dao

import org.jetbrains.exposed.sql.*
import uz.mahmudxon.model.User
import uz.mahmudxon.model.Users
import uz.mahmudxon.plugins.DatabaseFactory.dbQuery

class UserDaoImpl : UserDAO {
    override suspend fun getUser(id: Int): User? = dbQuery {
        Users.select { Users.id eq id }
            .map(::tableToUser)
            .singleOrNull()
    }

    override suspend fun getUsers(): List<User> {
        val result = ArrayList<User>()
        dbQuery {
            Users.selectAll()
                .map(::tableToUser)
        }?.let { result.addAll(it) }
        return result
    }

    override suspend fun insertUser(
        name: String,
        username: String,
        password: String,
        phone: String,
        email: String
    ): User? = dbQuery {
        val insertStatement = Users.insert {
            it[Users.name] = name
            it[Users.username] = username
            it[Users.password] = password
            it[Users.phone] = phone
            it[Users.email] = email
            it[imgUrl] = ""
            it[role] = User.Role.USER
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::tableToUser)
    }

    override suspend fun updateUser(user: User): User? = dbQuery {
        Users.update({ Users.id eq user.id }) {
            it[username] = user.username
            it[password] = user.password
            it[phone] = "${user.phone}"
            it[email] = "${user.email}"
        }
        getUser(user.id)
    }


    private fun tableToUser(row: ResultRow): User {
        return User(
            row[Users.id],
            row[Users.name],
            row[Users.username],
            row[Users.email],
            row[Users.phone],
            row[Users.password],
            row[Users.imgUrl],
            row[Users.role]
        )
    }
}