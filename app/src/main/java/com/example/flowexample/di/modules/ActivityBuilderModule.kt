package com.example.flowexample.di.modules

import com.example.flowexample.di.scopes.ActivityScope
import com.example.flowexample.ui.activities.LoginActivity
import com.example.flowexample.ui.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [ MainActivityModule::class])
    abstract fun provideMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ LoginActivityModule::class])
    abstract fun provideLoginActivity(): LoginActivity

}