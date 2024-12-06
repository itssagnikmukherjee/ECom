package com.sagnikmukherjee.ecommercedemo.data.di

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModules{

    @Provides
    fun provideFirebaseStorage() : FirebaseFirestore = FirebaseFirestore.getInstance()

}