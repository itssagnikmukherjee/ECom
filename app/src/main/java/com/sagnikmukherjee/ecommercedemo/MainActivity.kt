package com.sagnikmukherjee.ecommercedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.sagnikmukherjee.ecommercedemo.presentation.ui.screens.AddCategoryScreen
import com.sagnikmukherjee.ecommercedemo.presentation.ui.screens.AddProductScreen
import com.sagnikmukherjee.ecommercedemo.presentation.ui.theme.ECommerceDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ECommerceDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    AddCategoryScreen()
                    AddProductScreen()
                }
            }
        }
    }
}
