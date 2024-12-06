package com.sagnikmukherjee.ecommercedemo.presentation.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.sagnikmukherjee.ecommercedemo.domain.models.CategoryModel
import com.sagnikmukherjee.ecommercedemo.presentation.viewmodel.AddCategoryState
import com.sagnikmukherjee.ecommercedemo.presentation.viewmodel.AppViewModel


@Composable
fun AddCategoryScreen(viewModel: AppViewModel = hiltViewModel()) {

    val state = viewModel.addCategoryState.collectAsState()
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var categoryName by remember { mutableStateOf("") }
        var categoryImg by remember { mutableStateOf("") }

        OutlinedTextField(
            value = categoryName, onValueChange = {categoryName=it}, placeholder = { Text("Category Name") }
        )

        OutlinedTextField(
            value = categoryImg, onValueChange = {categoryImg=it}, placeholder = { Text("Category Image") }
        )

        Button(onClick = {
            viewModel.addCategory(CategoryModel(categoryName= categoryName, categoryIcon = categoryImg))
            Toast.makeText(context, "Category $categoryName Added Successfully", Toast.LENGTH_SHORT).show()
        }) {
            Text("Add Category")
        }


    }

}