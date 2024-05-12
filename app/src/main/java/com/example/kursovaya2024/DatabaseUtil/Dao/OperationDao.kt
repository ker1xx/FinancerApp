package com.example.kursovaya2024.DatabaseUtil.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.kursovaya2024.Models.DtoModel.OperationDto

@Dao
interface OperationDao {

    @Insert
    fun insertOperation(operation : OperationDto)

    @Update
    fun updateOperation(operation : OperationDto)

    @Delete
    fun deleteOperation(operation: OperationDto)

    @Query("SELECT * FROM [Operation] WHERE [User Id] == :userId")
    fun getOperations(userId : Int)
}