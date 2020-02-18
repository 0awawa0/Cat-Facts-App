package ru.awawa.catfactsapp

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.awawa.catfactsapp.retrofit.FactApi
import ru.awawa.catfactsapp.retrofit.Model
import java.lang.Exception


class MainPresenter(private val view: MainActivity) {

    private val tag = "MainPresenter"
    private val factApi: FactApi by lazy { Model.create() }

    fun getRandomFact() {
        GlobalScope.launch {
            try {
                val response = factApi.getRandomFact("cat,dog,horse,snail").execute()
                if (response.isSuccessful) {
                    view.showFact(response.body().text)
                } else {
                    view.showFact(view.getString(R.string.error_message))
                    Log.e(tag, response.raw().toString())
                }
            } catch (e: Exception) {
                view.showFact(view.getString(R.string.error_message))
                Log.e(tag, e.toString())
            }
        }
    }
}