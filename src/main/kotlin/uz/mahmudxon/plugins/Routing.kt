package uz.mahmudxon.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import uz.mahmudxon.db.dao.UserDaoImpl
import uz.mahmudxon.routing.auth.auth
import uz.mahmudxon.routing.index
import uz.mahmudxon.routing.userRoute

fun Application.configureRouting() {
    install(Routing) {
        val userDao = UserDaoImpl()
        auth(userDao)
        index()
        userRoute(userDao)
    }
}