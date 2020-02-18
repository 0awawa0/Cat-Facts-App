package ru.awawa.catfactsapp.retrofit


import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class Model {

    companion object {
        fun create(): FactApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://cat-fact.herokuapp.com/facts/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            return retrofit.create(FactApi::class.java)
        }
    }
}