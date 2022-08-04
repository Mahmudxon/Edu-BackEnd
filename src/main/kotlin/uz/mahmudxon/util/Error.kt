package uz.mahmudxon.util

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

data class ErrorResponse(val errorCode: Int, val errorMessage: String)

suspend fun ApplicationCall.respondError(code: HttpStatusCode, errorMessage: String?) {
    this.respond(ErrorResponse(errorCode = code.value, errorMessage = "$errorMessage"))
}