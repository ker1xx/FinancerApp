package com.example.kursovaya2024.DatabaseUtil.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.kursovaya2024.Models.DtoModel.BigSpendingDto

@Dao
interface DailyStatisticDao {
    @Insert
    fun insertDailyStatistic(bigSpend : BigSpendingDto)

    @Query("SELECT * FROM [Daily Statistic] WHERE [User Id] == :userId")
    fun getDailyStatistic(userId : Int)
}