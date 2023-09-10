package com.example.roomdatabasekotlion.interfaces

import com.example.roomdatabasekotlion.model.User

interface UpdateClickListener {

    fun update(user:User)
    fun delete(user:User)

}