package com.sagnikmukherjee.ecommercedemo.domain.models

data class ProductModel (
    val productName : String,
    val productDescription : String,
    val productActualPrice : String,
    val productBeforeDiscountPrice: String,
    val productImage : String,
    val availableUnits : Int,
    val isAvailable : Boolean = availableUnits > 0,
    val dateAdded : Long = System.currentTimeMillis()
)