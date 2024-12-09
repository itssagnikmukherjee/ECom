package com.sagnikmukherjee.ecommercedemo.presentation.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagnikmukherjee.ecommercedemo.domain.models.CategoryModel
import com.sagnikmukherjee.ecommercedemo.domain.models.ProductModel
import com.sagnikmukherjee.ecommercedemo.domain.repo.Repo
import com.sagnikmukherjee.ecommercedemo.presentation.state.ResultState
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

//    add product
    private val _addProductState = MutableStateFlow(AddProductState())
    val addProductState = _addProductState.asStateFlow()

    fun addProduct(product: ProductModel){
        viewModelScope.launch{
            repo.addProduct(product).collectLatest {

                when(it){
                    is ResultState.Loading -> {
                        _addProductState.value = AddProductState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _addProductState.value = AddProductState(isSuccess = it.data)
                    }

                    is ResultState.Error->{
                        _addProductState.value = AddProductState(error = it.message.toString())
                    }

                }

            }
        }
    }


//   add image to BB
    fun uploadImageToImgBB(imagePath: String, onResult: (String?) -> Unit) {
        val apiKey = "891c36f69bd8bc447943bea5f94459db"
        repo.uploadImageToImgBB(imagePath, apiKey, onResult)
    }


}


data class AddProductState(
    val isLoading: Boolean = false,
    val isSuccess: String = "",
    val error: String = ""
)

data class AddCategoryState(
    val isLoading: Boolean = false,
    val isSuccess: String = "",
    val error: String = ""
)