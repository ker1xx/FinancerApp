package com.example.kursovaya2024.DatabaseUtil.Dao

import androidx.room.Dao
import androidx.room.Query


@Dao
interface SpendingTypeDao {

    @Query("SELECT * FROM [Spending Type]")
    fun getIncomeType()
}