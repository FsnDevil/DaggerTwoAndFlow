package com.example.flowexample.ui.helpers

import javax.inject.Inject

class RandomDataCollectorImpl @Inject constructor(private val someData:String) : RandomDataCollector {
    override fun collectSomeRandomData():String {
        return someData
    }
}