package com.example.database.di

import com.example.database.data.RepositoryDatabase
import com.example.database.domain.repository.CardCollectionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindCardCollectionRepository(
        impl: RepositoryDatabase
    ): CardCollectionRepository
}