package com.sagnikmukherjee.ecommerceAdmin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagnikmukherjee.ecommerceAdmin.domain.models.CategoryModel
import com.sagnikmukherjee.ecommerceAdmin.domain.repo.Repo
import com.sagnikmukherjee.ecommerceAdmin.presentation.state.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repo: Repo) : ViewModel() {

    // add category

    private val _addCategoryState = MutableStateFlow(AddCategoryState())
    val addCategoryState = _addCategoryState.asStateFlow()

    fun addCategory(category: CategoryModel) {
            viewModelScope.launch{
                repo.addCategory(category).collectLatest{
                    when(it){
                        is ResultState.Loading -> {
                            _addCategoryState.value = AddCategoryState(isLoading = true)
                        }

                        is ResultState.Success -> {
                            _addCategoryState.value = AddCategoryState(isSuccess = it.data)
                        }

                        is ResultState.Error->{
                            _addCategoryState.value = AddCategoryState(error = it.message.toString())
                        }

                    }
                }
            }
    }


}

data class AddCategoryState(
    val isLoading: Boolean = false,
    val isSuccess: String = "",
    val error: String = ""
)