package com.sagnikmukherjee.ecomUser.domain.usecases

import com.sagnikmukherjee.ecomUser.domain.repo.Repo
import javax.inject.Inject

class GetAllCategoryUsecase @Inject constructor(private val repo: Repo) {

    fun getAllCategories() = repo.getAllCategories()


}
