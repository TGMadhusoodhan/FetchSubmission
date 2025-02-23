package com.madhusoodhan.fetchcoding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Item(val id: Int, val listId: Int, val name: String?)

interface FetchApi {
    @GET("hiring.json")
    suspend fun getItems(): List<Item>
}

object RetrofitInstance {
    private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"

    val api: FetchApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FetchApi::class.java)
    }
}
class FetchViewModel: ViewModel(){

    val items = liveData {
        val response = RetrofitInstance.api.getItems()
        val filteredSortedItems = response.filter { !it.name.isNullOrBlank() }
            .sortedWith(compareBy({ it.listId }, { it.name }))
        emit(filteredSortedItems)

    }

}
