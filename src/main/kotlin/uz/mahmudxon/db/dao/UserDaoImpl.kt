package uz.mahmudxon.db.dao

import org.jetbrains.exposed.sql.*
import uz.mahmudxon.model.User
import uz.mahmudxon.model.Users
import uz.mahmudxon.plugins.DatabaseFactory.dbQuery
import uz.mahmudxon.util.TransActionData

class UserDaoImpl : UserDAO {
    override suspend fun getUser(id: Int) = dbQuery {
        val user = Users.select { Users.id eq id }.map(::tableToUser).singleOrNull()
        user ?: throw IllegalArgumentException("User with id $id not found")
    }

    override suspend fun getUsers() = dbQuery {
        Users.selectAll().map(::tableToUser)
    }

    override suspend fun insertUser(
        user: User
    ) = dbQuery {
        val insertStatement = Users.insert {
            it[name] = user.name
            it[username] = user.username
            it[password] = user.password
            it[phone] = user.phone
            it[email] = user.email
            it[imgUrl] = user.imgUrl
            it[role] = user.role
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::tableToUser)!!
    }


    override suspend fun updateUser(user: User): TransActionData<User> {
        dbQuery {
            Users.update({ Users.id eq user.id }) {
                it[username] = user.username
                it[password] = user.password
                it[phone] = user.phone
                it[email] = user.email
            }
        }
        return getUser(user.id)
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