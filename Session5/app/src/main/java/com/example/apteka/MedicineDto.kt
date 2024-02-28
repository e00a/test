package com.example.apteka

data class MedicineDto(
    val medicineId: Int,
    val image: String,
    val manufacturer: String,
    val name: String,
    val price: Int,
    val stockQuantity: Int,
    val tradeName: String,
    val warehouseId: Int
)

