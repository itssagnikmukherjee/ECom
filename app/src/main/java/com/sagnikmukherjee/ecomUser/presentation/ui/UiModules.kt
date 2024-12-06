package com.sagnikmukherjee.ecomUser.presentation.ui

import com.google.firebase.firestore.FirebaseFirestore
import com.sagnikmukherjee.ecomUser.data.repoImpl.RepoImpl
import com.sagnikmukherjee.ecomUser.domain.repo.Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UiModules {

    @Provides
    @Singleton
    fun provideRepo(firestore: FirebaseFirestore) : Repo = RepoImpl(firestore)

}