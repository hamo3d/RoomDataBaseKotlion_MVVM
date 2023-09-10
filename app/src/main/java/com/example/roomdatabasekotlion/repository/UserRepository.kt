package com.example.roomdatabasekotlion.repository

import androidx.lifecycle.LiveData
import com.example.roomdatabasekotlion.data.UserDao
import com.example.roomdatabasekotlion.model.User

class UserRepository(private val userDao: UserDao) {


    val readAllData: LiveData<List<User>> = userDao.readAllData()


    suspend fun addUser (user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser (user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser (user: User){
        userDao.deleteUser(user)
    }
    suspend fun deleteAllUser (){
        userDao.deleteAllData()
    }

}