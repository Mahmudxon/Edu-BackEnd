package uz.mahmudxon.routing.auth

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import uz.mahmudxon.db.dao.UserDAO
import uz.mahmudxon.plugins.TokenManger
import uz.mahmudxon.util.TransActionData
import uz.mahmudxon.util.respondError

fun Routing.auth(userDAO: UserDAO) {
    route("/auth") {
        post("/login") {
            val login = call.receive<LoginRequest>()
            when (val t = userDAO.login(login.username, login.password)) {
                is TransActionData.Success -> {
                    call.respond(
                        LoginResponse(
                            user = t.data,
                            token = TokenManger.createToken(t.data.id)
                        )
                    )
                }
                is TransActionData.Error -> {
                    call.respondError(HttpStatusCode.BadRequest, t.error.message)
                }
            }
        }
        post("/register") {
            when (val transaction = userDAO.insertUser(call.receive())) {
                is TransActionData.Success -> call.respond(
                    LoginResponse(
                        user = transaction.data,
                        token = TokenManger.createToken(transaction.data.id)
                    )
                )
                is TransActionData.Error -> call.respondError(HttpStatusCode.BadRequest, "${transaction.error.message}")
            }
        }
    }
}