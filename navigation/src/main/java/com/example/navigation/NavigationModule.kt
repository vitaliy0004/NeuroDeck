package com.example.navigation

import com.example.core.AppNavigation
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {
    @Provides
     fun bindNavigationProvider():AppNavigation = Navigation()
}