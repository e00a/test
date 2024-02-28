package com.example.apteka.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.apteka.MedicineDto
import com.example.apteka.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun MedicinesScreen(navController: NavController, context: Context) {
    var medicines by remember {
        mutableStateOf<List<MedicineDto>>(emptyList())
    }
    var data = RestClient().client.getMedicines()
    data.enqueue(object : Callback<List<MedicineDto>?> {
        override fun onResponse(
            call: Call<List<MedicineDto>?>,
            response: Response<List<MedicineDto>?>
        ) {
            medicines = response.body() ?: emptyList()
        }

        override fun onFailure(call: Call<List<MedicineDto>?>, t: Throwable) {
Toast.makeText(context, "Ошибка подкючения", Toast.LENGTH_LONG).show()
        }
    })

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (medicines.isEmpty()) {
            CircularProgressIndicator()
        } else {
            LazyColumn() {
                items(medicines) {
                    MedicineItem(medicine = it, navController = navController)
                }
            }
            Button(onClick = { navController.navigate("post_medicine") }) {
                Text(text = "Добавить Препарат")
            }
        }
    }
}

@Composable
fun MedicineItem(medicine: MedicineDto, navController: NavController) {
    Column(Modifier.clickable {
        navController.navigate("medicine/${medicine.medicineId}")
    }) {
        Row {
            Text(text = medicine.name)
        }
        Text(text = "Количество " + medicine.stockQuantity)
    }
}