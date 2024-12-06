package com.sagnikmukherjee.ecomUser.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.sagnikmukherjee.ecomUser.presentation.viewModel.UserAppViewModel

@Composable
fun GetCategoriesScreen(
    viewModel: UserAppViewModel = hiltViewModel()
) {
    val state = viewModel.getAllCategoryState.collectAsState()

    Column {
        LazyRow {
            items(state.value.categoryData.size) {
                Text(text = state.value.categoryData[it]?.categoryName.toString())
                Text(state.value.categoryData[it]?.categoryIcon.toString())
            }
        }
    }
}