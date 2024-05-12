package com.example.kursovaya2024.Models.DtoModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "Operation")
data class OperationDto(
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "UserId") val userId : Int,
    @ColumnInfo(name = "Time", defaultValue = "CURRENT_TIMESTAMP") val time : Date,
    @ColumnInfo(name = "SpendingTypeId") val spendingTypeId : Int?,
    @ColumnInfo(name = "IncomeTypeId") val incomeTypeId : Int?,
    @ColumnInfo(name = "Amount") val amount : Float
)