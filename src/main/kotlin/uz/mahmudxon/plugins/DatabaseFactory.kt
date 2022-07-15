package uz.mahmudxon.plugins

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import uz.mahmudxon.model.*
import uz.mahmudxon.util.TransActionData

object DatabaseFactory {
    fun init() {
        val database = Database.connect(
            url = "jdbc:postgresql://localhost:5432/edu",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "1111"
        )
        transaction(database) {
            SchemaUtils.create(
                Users, Subjects, Lessons, Topics, Contents, Languages, Devices, Sessions
            )
        }
    }

    suspend fun <T : Any> dbQuery(block: suspend () -> T): TransActionData<T> =
        newSuspendedTransaction(Dispatchers.IO) {
            try {
                val data = block()
                TransActionData.Success(data)
            } catch (e: Exception) {
                TransActionData.Error(e)
            }
        }
}