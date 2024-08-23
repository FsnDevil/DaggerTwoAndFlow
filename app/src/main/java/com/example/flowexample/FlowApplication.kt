package com.example.flowexample

import com.example.flowexample.di.components.AppComponent
import com.example.flowexample.di.modules.AppModule
import com.example.flowexample.di.components.DaggerAppComponent
import com.example.flowexample.di.helpers.RandomCollectorModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

open class FlowApplication : DaggerApplication() {
    open lateinit var appComponent: AppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent.builder()
            .baseUrl(BASE_URL)
            .randomString("Random String")
            .appModule(AppModule(BASE_URL,"Data from Irfan.."))
            .application(this).build()
        return appComponent
    }

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

}