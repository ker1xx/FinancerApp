package com.example.kursovaya2024.DatabaseUtil.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.kursovaya2024.Models.DtoModel.BigSpendingDto

@Dao
interface BigSpendingDao {

    @Insert
    fun insertBigSpending(bigSpend : BigSpendingDto)

    @Update
    fun updateBigSpending(bigSpend : BigSpendingDto)

    @Delete
    fun deleteBigSpending(bigSpend: BigSpendingDto)

    @Query("SELECT * FROM [Big Spending] WHERE [User Id] == :userId")
    fun getBigSpending(userId : Int)

}