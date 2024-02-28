package com.example.apteka.screens

import android.transition.CircularPropagation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.apteka.MedicineDto
import com.example.apteka.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun MedicineScreen(id: Int) {
    var medicine by remember {
        mutableStateOf<MedicineDto?>(null)
    }
    var data = RestClient().client.getMedicine(id)
    data.enqueue(object : Callback<MedicineDto?> {
        override fun onResponse(call: Call<MedicineDto?>, response: Response<MedicineDto?>) {
            medicine = response.body()
        }

        override fun onFailure(call: Call<MedicineDto?>, t: Throwable) {

        }
    })
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (medicine == null)
            CircularProgressIndicator()
        else
            MedicineInfo(medicine!!)
    }
}

@Composable
fun MedicineInfo(medicine: MedicineDto) {
    val warehouse =
        if (medicine.warehouseId == 1) "Склад №1" else if (medicine.warehouseId == 2) "Склад №1" else "Склад №3"
    Column(
    ) {
        Text(text = "" + warehouse)
        Text(text = "количество на складе" + medicine.stockQuantity)
        Text(text = "" + medicine.name)
        Text(text = "" + medicine.manufacturer)
        Text(text = "" + medicine.price)
    }
}