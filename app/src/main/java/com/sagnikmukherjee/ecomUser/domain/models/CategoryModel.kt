package com.sagnikmukherjee.ecomUser.domain.models

data class CategoryModel(
    var categoryName: String,
    var categoryIcon: String,
    val dateAdded : Long = System.currentTimeMillis()
)