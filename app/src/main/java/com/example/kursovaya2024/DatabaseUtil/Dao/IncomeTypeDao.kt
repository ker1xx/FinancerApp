package com.example.kursovaya2024.DatabaseUtil.Dao

import androidx.room.Dao
import androidx.room.Query


@Dao
interface IncomeTypeDao {

    @Query("SELECT * FROM [Income Type]")
    fun getIncomeType()
}