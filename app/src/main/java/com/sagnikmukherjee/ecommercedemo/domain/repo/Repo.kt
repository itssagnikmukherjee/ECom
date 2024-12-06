package com.sagnikmukherjee.ecommercedemo.domain.repo

import com.sagnikmukherjee.ecommercedemo.domain.models.CategoryModel
import com.sagnikmukherjee.ecommercedemo.presentation.state.ResultState
import kotlinx.coroutines.flow.Flow

interface Repo {
    fun addCategory(category: CategoryModel) : Flow<ResultState<String>>

}