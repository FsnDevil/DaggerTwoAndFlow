package com.example.flowexample.di.helpers

import com.example.flowexample.ui.helpers.RandomDataCollector
import com.example.flowexample.ui.helpers.RandomDataCollectorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RandomCollectorModule(private val someData:String) {

    @Binds
    abstract fun provideRandomCollector(randomDataCollectorImpl: RandomDataCollectorImpl):RandomDataCollector

}