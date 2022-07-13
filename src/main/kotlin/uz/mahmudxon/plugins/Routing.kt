package uz.mahmudxon.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import uz.mahmudxon.api.index

fun Application.configureRouting() {
    install(Routing) {
        index()
    }
}