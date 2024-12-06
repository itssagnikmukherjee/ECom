package com.sagnikmukherjee.ecomUser.data.repoImpl

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.sagnikmukherjee.ecomUser.domain.models.CategoryModel
import com.sagnikmukherjee.ecomUser.domain.repo.Repo
import com.sagnikmukherjee.ecomUser.presentation.states.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import com.sagnikmukherjee.ecomUser.utils.constants.Constants.CATEGORY
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose

class RepoImpl @Inject constructor(private val firebaseStorage: FirebaseFirestore) : Repo {
    override fun getAllCategories(): Flow<ResultState<List<CategoryModel>>> = callbackFlow{
        trySend(ResultState.Loading)
        firebaseStorage.collection(CATEGORY).limit(6).get().addOnSuccessListener {
            val data = it.documents.mapNotNull{
                it.toObject(CategoryModel::class.java)
            }
            trySend(ResultState.Success(data))
            Log.d("TAG", "success getAllCategories: ${it.toObjects(CategoryModel::class.java)}")
        }.addOnFailureListener{
            trySend(ResultState.Error(it.message.toString()))
            Log.d("TAG", "error getAllCategories: ${it.message.toString()}")
        }
        awaitClose {
            cancel()
        }
    }
}