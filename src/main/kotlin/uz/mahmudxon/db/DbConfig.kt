package uz.mahmudxon.db

object DbConfig {
    private val host = System.getenv("DB_HOST")
    private val port = System.getenv("DB_PORT")
    private val dbName = System.getenv("DB_NAME")
    val dbUser = System.getenv("DB_USER")
    val dbPassword = System.getenv("DB_PASSWORD")
    val dbUrl = "jdbc:postgresql://$host:$port/$dbName"
    val dbDriver = "org.postgresql.Driver"
}