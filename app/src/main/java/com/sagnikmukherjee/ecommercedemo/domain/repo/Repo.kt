package com.sagnikmukherjee.ecommercedemo.domain.repo

import android.net.Uri
import com.sagnikmukherjee.ecommercedemo.domain.models.CategoryModel
import com.sagnikmukherjee.ecommercedemo.domain.models.ProductModel
import com.sagnikmukherjee.ecommercedemo.presentation.state.ResultState
import kotlinx.coroutines.flow.Flow

interface Repo {
    fun addCategory(category: CategoryModel) : Flow<ResultState<String>>
    fun addProduct(product: ProductModel) : Flow<ResultState<String>>

//    fun addImage(image: Uri) : Flow<ResultState<String>>


}