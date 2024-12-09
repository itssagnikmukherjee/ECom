package com.sagnikmukherjee.ecomUser.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.sagnikmukherjee.ecomUser.presentation.viewModel.UserAppViewModel

@Composable
fun ProductsScreen(viewModel: UserAppViewModel = hiltViewModel()) {

    val state = viewModel.getAllProductState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllProducts()
    }

    LazyRow {
        items(state.value.productData.size) {
            Column {
            Text(text = state.value.productData[it]?.productName.toString())
            Text(text = state.value.productData[it]?.productDescription.toString())
            Text(text = state.value.productData[it]?.productActualPrice.toString())
            Text(text = state.value.productData[it]?.productBeforeDiscountPrice.toString())

                AsyncImage(
                    model = state.value.productData[it]?.productImage.toString(),
                    contentDescription = "product image"
                )

            }
        }

    }

}