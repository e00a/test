package com.example.apteka

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @GET("api/Medicines")
    fun getMedicines(): Call<List<MedicineDto>>

    @GET("api/Medicines/{id}")
    fun getMedicine(@Path("id") id:Int): Call<MedicineDto>

    @POST("api/Medicines")
    suspend fun postMedicine(@Body medicine:MedicineDto): ResponseBody

}