package com.sagnikmukherjee.ecommercedemo.data.repoImpl

import android.net.Uri
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.firestore.FirebaseFirestore
import com.sagnikmukherjee.ecommercedemo.utils.constants.Constants
import com.sagnikmukherjee.ecommercedemo.domain.models.CategoryModel
import com.sagnikmukherjee.ecommercedemo.domain.models.ProductModel
import com.sagnikmukherjee.ecommercedemo.domain.repo.Repo
import com.sagnikmukherjee.ecommercedemo.presentation.state.ResultState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.File
import javax.inject.Inject

class repoImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : Repo{

    //adding the category to the firebase

    override fun addCategory(category: CategoryModel): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)
        firestore.collection(Constants.CATEGORY).add(category).addOnSuccessListener {
            trySend(ResultState.Success("Category Added Successfully"))
            Log.d("TAG", "addCategory: Category Added Successfully")
        }.addOnFailureListener{
            trySend(ResultState.Error(it.message.toString()))
        }

        awaitClose{
            close()
        }

    }


//  adding products to the firebase
    override fun addProduct(product: ProductModel): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)
        firestore.collection(Constants.PRODUCTS).add(product).addOnSuccessListener {
            trySend(ResultState.Success("Product Added Successfully"))
            Log.d("TAG", "addProduct: Product Added Successfully")
        }.addOnFailureListener{
            trySend(ResultState.Error(it.message.toString()))
        }
    awaitClose(){
        close()
    }

    }


//  upload image to BB
override fun uploadImageToImgBB(imagePath: String, apiKey: String, onResult: (String?) -> Unit) {
    val client = OkHttpClient()
    val imageFile = File(imagePath)
    val encodedImage = imageFile.readBytes().let { Base64.encodeToString(it, Base64.DEFAULT) }

    val requestBody = MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("key", apiKey)
        .addFormDataPart("image", encodedImage)
        .build()

    val request = Request.Builder()
        .url("https://api.imgbb.com/1/upload")
        .post(requestBody)
        .build()

    Thread {
        try {
            val response = client.newCall(request).execute()
            val responseBody = response.body?.string()
            if (response.isSuccessful && responseBody != null) {
                val imageUrl = JSONObject(responseBody)
                    .getJSONObject("data")
                    .getString("url")
                onResult(imageUrl)
            } else {
                onResult(null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            onResult(null)
        }
    }.start()
}




}