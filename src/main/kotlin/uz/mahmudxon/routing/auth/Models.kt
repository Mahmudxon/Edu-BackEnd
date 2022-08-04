package uz.mahmudxon.routing.auth

import uz.mahmudxon.model.User

data class LoginRequest(val username : String, val password: String)
data class LoginResponse(val token: String, val user: User)