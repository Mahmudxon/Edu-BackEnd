package uz.mahmudxon.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import uz.mahmudxon.dao.UserDAO
import uz.mahmudxon.util.TransActionData
import uz.mahmudxon.util.respondError

fun Routing.userRoute(userDAO: UserDAO) {
    route("/user") {
        get {
            call.respond(userDAO.getUsers())
        }

        post {
            when (val transaction = userDAO.insertUser(call.receive())) {
                is TransActionData.Success -> call.respond(transaction.data)
                is TransActionData.Error -> call.respondError(HttpStatusCode.BadRequest, "${transaction.error.message}")
            }
        }
    }
}
