package com.example.apteka.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Outline
import com.example.apteka.MedicineDto
import com.example.apteka.RestClient
import kotlinx.coroutines.launch

@Composable
fun AddMedicine() {
    var coroutineScope = rememberCoroutineScope()
    var name by remember {
        mutableStateOf("")
    }
    var manufacturer by remember {
        mutableStateOf("")
    }
    var price by remember {
        mutableIntStateOf(0)
    }
    var stockQuantity by remember {
        mutableIntStateOf(0)
    }
    var tradeName by remember {
        mutableStateOf("")
    }
    var warehouseId by remember {
        mutableIntStateOf(2)
    }

    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = name,
            onValueChange = { name = it },
            label = { Text(text = "название") })
        OutlinedTextField(value = manufacturer,
            onValueChange = { manufacturer = it },
            label = { Text(text = "производитель") })
        OutlinedTextField(value = price.toString(),
            onValueChange = { v -> price = v.toInt() },
            label = { Text(text = "цена") })
        OutlinedTextField(value = stockQuantity.toString(),
            onValueChange = { v -> stockQuantity = v.toInt() },
            label = { Text(text = "Количество") })
        OutlinedTextField(value = warehouseId.toString(),
            onValueChange = { v -> warehouseId = v.toInt() },
            label = { Text(text = "Номер склада") })
        OutlinedTextField(value = tradeName,
            onValueChange = { tradeName = it },
            label = { Text(text = "trade name") })
        Button(onClick = {
            val medicineDto = MedicineDto(
                warehouseId = warehouseId,
                stockQuantity = stockQuantity,
                medicineId = 0,
                tradeName = tradeName,
                price = price,
                manufacturer = manufacturer,
                image = "---",
                name = name
            )
            coroutineScope.launch {
                RestClient().client.postMedicine(medicineDto)
            }
        }) {
            Text(text = "Дабавить препарат")
        }
    }


}