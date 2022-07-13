package uz.mahmudxon.api

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.index() {
    get("/") {
        call.respondText("Hello World!")
    }
}