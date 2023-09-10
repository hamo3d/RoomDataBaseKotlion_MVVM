package com.example.roomdatabasekotlion.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomdatabasekotlion.model.User


@Dao
interface UserDao {


    //onConflict = OnConflictStrategy.IGNORE  إذا كان هناك سجل موجود بنفس المفتاح الرئيسي، فلن يتم إجراء أي تغيير في قاعدة البيانات.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)


    @Delete
    suspend fun deleteUser(user: User)

    @Query("delete  from user_name ")
    suspend fun deleteAllData()

    @Query("select * from user_name order by id asc")
    fun readAllData(): LiveData<List<User>>


}