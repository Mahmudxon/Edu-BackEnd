package uz.mahmudxon.plugins

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import uz.mahmudxon.dao.UserDAO
import uz.mahmudxon.dao.UserDaoImpl
import uz.mahmudxon.model.*

object DatabaseFactory {
    fun init() {
        val database = Database.connect(
            "jdbc:postgresql://localhost:5432/edu", driver = "org.postgresql.Driver",
            user = "postgres", password = "1111"
        )
        transaction(database)
        {
            SchemaUtils.create(
                Users,
                Subjects,
                Lessons,
                Topics,
                Contents,
                Languages,
                Devices,
                Sessions
            )
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    fun getUserDao(): UserDAO = UserDaoImpl()
}