package com.example.kursovaya2024.Models.DtoModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "User")
data class UserDto(
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "Name") val name : String,
    @ColumnInfo(name = "Identificator") val identificator : String,
    @ColumnInfo(name = "IMEI") val imei : String
)