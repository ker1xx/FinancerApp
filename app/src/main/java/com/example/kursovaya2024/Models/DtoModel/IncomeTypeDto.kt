package com.example.kursovaya2024.Models.DtoModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "IncomeType")
data class IncomeTypeDto(
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "Name") val name : String
)