package uz.mahmudxon.dao

import uz.mahmudxon.model.User

interface UserDAO {
    suspend fun getUser(id: Int): User?
    suspend fun getUsers(): List<User>
    suspend fun insertUser(username: String, password: String, phone: String, email: String): User?
    suspend fun updateUser(user: User): User?
}