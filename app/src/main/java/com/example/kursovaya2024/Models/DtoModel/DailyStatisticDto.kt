package com.example.kursovaya2024.Models.DtoModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "DailyStatistic")
data class DailyStatisticDto(
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "UserId") val userId : Int,
    @ColumnInfo(name = "Date") val date : Date,
    @ColumnInfo(name = "PercentageOfSpending") val percentageOfSpending : Int
)