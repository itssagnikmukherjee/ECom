package com.sagnikmukherjee.ecomUser.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagnikmukherjee.ecomUser.domain.models.CategoryModel
import com.sagnikmukherjee.ecomUser.domain.usecases.GetAllCategoryUsecase
import com.sagnikmukherjee.ecomUser.presentation.states.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserAppViewModel @Inject constructor(private val getAllCategoryUsecase: GetAllCategoryUsecase) : ViewModel(){

    private val _getAllCategoryState = MutableStateFlow(GetCategoryState())
    val getAllCategoryState = _getAllCategoryState.asStateFlow()
    fun getAllCategories(){
        viewModelScope.launch{
            getAllCategoryUsecase.getAllCategories().collectLatest {
                when(it){
                    is ResultState.Loading -> {
                        _getAllCategoryState.value = GetCategoryState(isLoading = true)
                    }
                    is ResultState.Success -> {
                        _getAllCategoryState.value = GetCategoryState(categoryData = it.data)
                    }
                    is ResultState.Error -> {
                        _getAllCategoryState.value = GetCategoryState(error = it.message.toString())
                    }
                }
            }
        }
    }

}

data class GetCategoryState(
    val isLoading: Boolean = false,
    val categoryData: List<CategoryModel?> = emptyList(),
    val error: String = ""
)