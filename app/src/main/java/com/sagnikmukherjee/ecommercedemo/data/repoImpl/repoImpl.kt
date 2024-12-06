package com.sagnikmukherjee.ecommercedemo.data.repoImpl

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.firestore.FirebaseFirestore
import com.sagnikmukherjee.ecommercedemo.utils.constants.Constants
import com.sagnikmukherjee.ecommercedemo.domain.models.CategoryModel
import com.sagnikmukherjee.ecommercedemo.domain.repo.Repo
import com.sagnikmukherjee.ecommercedemo.presentation.state.ResultState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
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



}