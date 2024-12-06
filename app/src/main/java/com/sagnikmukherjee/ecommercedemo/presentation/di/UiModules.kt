package com.sagnikmukherjee.ecommercedemo.presentation.di

import com.google.firebase.firestore.FirebaseFirestore
import com.sagnikmukherjee.ecommercedemo.data.repoImpl.repoImpl
import com.sagnikmukherjee.ecommercedemo.domain.models.CategoryModel
import com.sagnikmukherjee.ecommercedemo.domain.repo.Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UiModules{

    @Provides
    @Singleton
    fun provideRepo(
        firestore: FirebaseFirestore
    ) : Repo{
        return repoImpl(firestore)
    }

}