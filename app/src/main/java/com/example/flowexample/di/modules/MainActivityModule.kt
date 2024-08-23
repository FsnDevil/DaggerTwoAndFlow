package com.example.flowexample.di.modules

import com.example.flowexample.api.ApiService
import com.example.flowexample.di.scopes.ActivityScope
import com.example.flowexample.repositories.UserRepository
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @ActivityScope
    @Provides
    fun provideUserRepository(apiService: ApiService): UserRepository = UserRepository(apiService)

}