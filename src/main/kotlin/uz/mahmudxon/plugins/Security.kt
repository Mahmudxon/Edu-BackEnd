package uz.mahmudxon.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.config.*
import java.util.*

fun Application.configureSecurity() {
    authentication {
        jwt("auth-jwt") {

        }
    }
}

object TokenManger {


 private val myRealm = "Access to 'hello'"
    private val secret = "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
    private val issuer = "http://0.0.0.0:8080/"
    private val audience = "http://0.0.0.0:8080/hello"
    private val algorithm = Algorithm.HMAC256(secret)

//    fun init(config: ApplicationConfig) {
//        secret = config.property("jwt.secret").getString()
//        issuer = config.property("jwt.issuer").getString()
//        audience = config.property("jwt.audience").getString()
//        myRealm = config.property("jwt.myRealm").getString()
//    }

    fun createToken(userId: Int): String {
        return JWT.create()
            .withIssuer(issuer)
            .withAudience(audience)
            .withClaim("userId", userId)
            .withExpiresAt(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
            .sign(algorithm)
    }

    fun getUserId(token: String): Int? {
        return JWT.require(algorithm)
            .withIssuer(issuer)
            .withAudience(audience)
            .build()
            .verify(token)
            .getClaim("userId").asInt()
    }

    fun isTokenExpired(token: String): Boolean {
        return JWT.decode(token)
            .expiresAt.time < System.currentTimeMillis()
    }

}