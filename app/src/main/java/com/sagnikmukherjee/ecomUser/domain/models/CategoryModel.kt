package com.sagnikmukherjee.ecomUser.domain.models

data class CategoryModel(
    val categoryName: String,
    val categoryIcon: String,
    val dateAdded: Any? = ""
){
    constructor() : this("","")
}