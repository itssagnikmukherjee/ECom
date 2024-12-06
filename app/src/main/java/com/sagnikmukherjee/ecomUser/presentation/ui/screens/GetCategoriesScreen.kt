package com.sagnikmukherjee.ecomUser.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sagnikmukherjee.ecomUser.presentation.viewModel.UserAppViewModel
import java.nio.file.WatchEvent
import java.text.SimpleDateFormat

@Composable
fun GetCategoriesScreen(
    viewModel: UserAppViewModel = hiltViewModel()
) {
    val state = viewModel.getAllCategoryState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllCategories()
    }

        LazyRow (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            items(state.value.categoryData.size) {
               Card {
                Text(text = state.value.categoryData[it]?.categoryName.toString())
                Text(state.value.categoryData[it]?.categoryIcon.toString())
//                   Text(SimpleDateFormat("dd/MM/yyyy").format(state.value.categoryData[it]?.dateAdded))
               }
                }
            }

    }