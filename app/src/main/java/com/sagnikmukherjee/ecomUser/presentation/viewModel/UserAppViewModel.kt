package com.sagnikmukherjee.ecomUser.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagnikmukherjee.ecomUser.domain.models.CategoryModel
import com.sagnikmukherjee.ecomUser.domain.models.ProductModel
import com.sagnikmukherjee.ecomUser.domain.usecases.GetAllCategoryUsecase
import com.sagnikmukherjee.ecomUser.domain.usecases.GetAllProductUsecase
import com.sagnikmukherjee.ecomUser.presentation.states.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserAppViewModel @Inject constructor(private val getAllCategoryUsecase: GetAllCategoryUsecase,
    private val getAllProductUsecase: GetAllProductUsecase
    ) : ViewModel(){

//    getting all categories from firebase
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

//    getting all products from firebase
    private val _getAllProductState = MutableStateFlow(GetProductState())
    val getAllProductState = _getAllProductState.asStateFlow()

    fun getAllProducts(){
        viewModelScope.launch {

            getAllProductUsecase.getAllProducts().collectLatest{
                when(it){
                    is ResultState.Loading -> {
                        _getAllProductState.value = GetProductState(isLoading = true)
                    }
                    is ResultState.Success -> {
                        _getAllProductState.value = GetProductState(productData = it.data)
                    }
                    is ResultState.Error -> {
                        _getAllProductState.value = GetProductState(error = it.message.toString())
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

data class GetProductState(
    val isLoading: Boolean = false,
    val productData: List<ProductModel?> = emptyList(),
    val error: String = ""
)