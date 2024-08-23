package com.example.flowexample.di.components

import android.app.Application
import com.example.flowexample.FlowApplication
import com.example.flowexample.di.helpers.RandomCollectorModule
import com.example.flowexample.di.modules.ActivityBuilderModule
import com.example.flowexample.di.modules.AppModule
import com.example.flowexample.di.modules.ViewModelsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class, ViewModelsModule::class, AppModule::class,RandomCollectorModule::class]
)
interface AppComponent : AndroidInjector<FlowApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun baseUrl(@BaseUrl url:String): Builder

        @BindsInstance
        fun randomString(@RandomString url:String): Builder

        @BindsInstance
        fun application(application: Application): Builder

        fun appModule(appModule: AppModule): Builder


        fun build(): AppComponent
    }

}

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
annotation class BaseUrl

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
annotation class RandomString