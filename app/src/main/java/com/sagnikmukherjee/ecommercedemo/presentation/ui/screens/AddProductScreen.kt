package com.sagnikmukherjee.ecommercedemo.presentation.ui.screens

import android.content.Intent
import android.graphics.Outline
import android.net.Uri
import android.os.FileUtils
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.rememberAsyncImagePainter
import com.sagnikmukherjee.ecommercedemo.domain.models.ProductModel
import com.sagnikmukherjee.ecommercedemo.presentation.viewmodel.AppViewModel
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.File
import android.util.Base64

@Preview(showBackground = true)
@Composable
fun AddProductScreen(viewModel: AppViewModel = hiltViewModel()) {
    var productName by remember { mutableStateOf("") }
    var productDescription by remember { mutableStateOf("") }
    var productActualPrice by remember { mutableStateOf("") }
    var productBeforeDiscountPrice by remember { mutableStateOf("") }
    var productImage by remember { mutableStateOf("") }
    var productImageUri by remember { mutableStateOf<Uri?>(null) }
    var availableUnits by remember { mutableIntStateOf(0) }
    var dateAdded by remember { mutableLongStateOf(System.currentTimeMillis()) }
    val context = LocalContext.current

    // Image Picker
    val imagePickerLauncher: ActivityResultLauncher<String> =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                productImageUri = it
            }
        }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = productName,
            onValueChange = { productName = it },
            label = { Text("Product Name") }
        )
        OutlinedTextField(
            value = productDescription,
            onValueChange = { productDescription = it },
            label = { Text("Product Desc") }
        )
        OutlinedTextField(
            value = productActualPrice,
            onValueChange = { productActualPrice = it },
            label = { Text("Product Actual Price") }
        )
        OutlinedTextField(
            value = productBeforeDiscountPrice,
            onValueChange = { productBeforeDiscountPrice = it },
            label = { Text("Product Discount Price") }
        )
        OutlinedTextField(
            value = availableUnits.toString(),
            onValueChange = { availableUnits = it.toIntOrNull() ?: 0 },
            label = { Text("Product Units") }
        )

        // Image Picker Button
        Button(onClick = { imagePickerLauncher.launch("image/*") }) {
            Text("Select Image")
        }

        // Display the selected image
        if (productImageUri != null) {
            val painter = rememberAsyncImagePainter(productImageUri)
            Surface(
                modifier = Modifier
                    .padding(16.dp)
                    .size(300.dp),
                shape = RoundedCornerShape(20.dp),
                shadowElevation = 10.dp
            ) {
                Image(
                    painter = painter,
                    contentDescription = "Selected Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        } else {
            Text("No image selected")
        }

        Button(onClick = {
            if (productImageUri != null) {
                val imagePath = com.sagnikmukherjee.ecommercedemo.utils.FileUtils.getPath(context, productImageUri!!)
                if (imagePath != null) {
                    viewModel.uploadImageToImgBB(imagePath) { imageUrl ->
                        if (imageUrl != null) {
                            productImage = imageUrl
                            viewModel.addProduct(
                                ProductModel(
                                    productName = productName,
                                    productDescription = productDescription,
                                    productActualPrice = productActualPrice,
                                    productBeforeDiscountPrice = productBeforeDiscountPrice,
                                    productImage = productImage,
                                    availableUnits = availableUnits,
                                    dateAdded = dateAdded
                                )
                            )
                            Handler(Looper.getMainLooper()).post{
                                Toast.makeText(context, "Product added Successfully", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Handler(Looper.getMainLooper()).post{
                                Toast.makeText(context, "Product failed to upload", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            } else {
                Handler(Looper.getMainLooper()).post{
                    Toast.makeText(context, "Please select an image", Toast.LENGTH_SHORT).show()
                }
            }
        }) {
            Text("Add Product")
        }
    }
}