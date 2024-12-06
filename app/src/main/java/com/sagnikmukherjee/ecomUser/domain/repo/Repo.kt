package com.sagnikmukherjee.ecomUser.domain.repo

import com.sagnikmukherjee.ecomUser.domain.models.CategoryModel
import com.sagnikmukherjee.ecomUser.presentation.states.ResultState
import kotlinx.coroutines.flow.Flow

interface Repo {
    fun getAllCategories() : Flow<ResultState<List<CategoryModel>>>
}