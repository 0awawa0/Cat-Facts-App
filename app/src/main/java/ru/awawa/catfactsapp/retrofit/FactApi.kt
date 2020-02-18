package ru.awawa.catfactsapp.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FactApi {
    @GET("random")
    fun getRandomFact(@Query("animal_type") animalType: String) : Call<FactDataModel>
}