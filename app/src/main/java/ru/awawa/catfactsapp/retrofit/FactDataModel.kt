package ru.awawa.catfactsapp.retrofit

data class FactDataModel(
    val _id: String,
    val _v: Int,
    val user: String,
    val text: String,
    val updateAt: Long,
    val sendDate: Long,
    val deleted: Boolean,
    val source: String,
    val used: Boolean,
    val type: String
)