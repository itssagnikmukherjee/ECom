package com.sagnikmukherjee.ecommerceAdmin.domain.repo

import com.sagnikmukherjee.ecommerceAdmin.domain.models.CategoryModel
import com.sagnikmukherjee.ecommerceAdmin.presentation.state.ResultState
import kotlinx.coroutines.flow.Flow

interface Repo {
    fun addCategory(category: CategoryModel) : Flow<ResultState<String>>

}