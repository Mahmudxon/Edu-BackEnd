package uz.mahmudxon

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import uz.mahmudxon.plugins.DatabaseFactory
import uz.mahmudxon.plugins.configureRouting
import uz.mahmudxon.plugins.configureSerialization

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
        DatabaseFactory.init()
    }.start(wait = true)
}