package com.example.kursovaya2024.Utility.API

import com.example.kursovaya2024.Models.DtoModel.BigSpendingDto
import retrofit2.Call
import retrofit2.http.GET

interface FinancerApiInterface {

    //functions for bigSpending

    @GET("userId")
    fun getBigSpendingList(userId : Int) : Call<List<BigSpendingDto>>


}