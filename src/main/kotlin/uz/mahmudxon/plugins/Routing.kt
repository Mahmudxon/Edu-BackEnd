package uz.mahmudxon.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import uz.mahmudxon.dao.UserDaoImpl
import uz.mahmudxon.routing.index
import uz.mahmudxon.routing.userRoute

fun Application.configureRouting() {
    install(Routing) {
        index()
        userRoute(UserDaoImpl())
    }
}