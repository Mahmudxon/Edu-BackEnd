package uz.mahmudxon.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import uz.mahmudxon.dao.UserDAO
import uz.mahmudxon.util.respondError

fun Routing.userRoute(userDAO: UserDAO) {
    route("/user") {
        get {
            call.respond(userDAO.getUsers())
        }

        post {
            with(call) {
                val params: HashMap<String, String> = receive()
                val name: String = requireNotNull(params["name"])
                val username: String = requireNotNull(params["username"])
                val password: String = requireNotNull(params["password"])
                val phone: String = requireNotNull(params["phone"])
                val email: String = params["email"] ?: ""
                val user = userDAO.insertUser(name, username, password, phone, email)
                if (user != null) {
                    respond(HttpStatusCode.Created, user)
                } else {
                    respondError(HttpStatusCode.BadRequest, "User already exists")
                }
            }
        }
    }
}
