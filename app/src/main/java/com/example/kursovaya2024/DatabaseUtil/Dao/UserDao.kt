package com.example.kursovaya2024.DatabaseUtil.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.kursovaya2024.Models.DtoModel.BigSpendingDto
import com.example.kursovaya2024.Models.DtoModel.UserDto

@Dao
interface UserDao {
    @Insert
    fun insertUser(user : UserDto)

    @Update
    fun updateUser(user : UserDto)

    @Delete
    fun deleteUser(user : UserDto)

    @Query("SELECT * FROM [Users]")
    fun getUser(userId : Int)
}