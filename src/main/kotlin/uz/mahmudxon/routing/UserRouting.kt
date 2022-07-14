package uz.mahmudxon.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import uz.mahmudxon.dao.UserDAO

fun Routing.userRoute(userDAO: UserDAO) {
    route("/user") {
        get {
            call.respond(userDAO.getUsers())
        }

        post {
            with(call)
            {
                val parameters = receiveParameters()
                val name = requireNotNull(parameters["name"])
                val username = requireNotNull(parameters["username"])
                val password = requireNotNull(parameters["password"])
                val phone = requireNotNull(parameters["phone"])
                val email = parameters["email"] ?: ""
                val user = userDAO.insertUser(name, username, password, phone, email)
                if (user != null) {
                    call.respond(HttpStatusCode.Created, user)
                } else {
                    call.respond(HttpStatusCode.BadRequest, "User already exists")
                }
            }
        }
    }
}