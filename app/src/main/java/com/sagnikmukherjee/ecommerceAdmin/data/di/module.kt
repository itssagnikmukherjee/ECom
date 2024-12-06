package com.sagnikmukherjee.ecommerceAdmin.data.di

import com.google.firebase.firestore.FirebaseFirestore
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