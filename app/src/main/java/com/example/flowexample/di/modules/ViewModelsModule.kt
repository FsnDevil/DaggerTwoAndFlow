package com.example.flowexample.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flowexample.di.viewmodels.ViewModelFactory
import com.example.flowexample.di.viewmodels.ViewModelKey
import com.example.flowexample.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun meetingViewModel(viewModel: MainViewModel): ViewModel
}