package com.example.kursovaya2024.Models

class DateNameCardModel(private var dateName: String?) {

    fun getDateName(): String? {
        return dateName
    }

    fun setDateName(dateName: String?) {
        this.dateName = dateName
    }
}