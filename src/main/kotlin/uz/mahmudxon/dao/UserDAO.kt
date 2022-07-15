package uz.mahmudxon.dao

import uz.mahmudxon.model.User
import uz.mahmudxon.util.TransActionData

interface UserDAO {
    suspend fun getUser(id: Int): TransActionData<User>
    suspend fun getUsers(): TransActionData<List<User>>
    suspend fun insertUser(user: User): TransActionData<User>
    suspend fun updateUser(user: User): TransActionData<User>
}