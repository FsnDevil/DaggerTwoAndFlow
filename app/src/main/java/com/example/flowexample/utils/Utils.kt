package com.example.flowexample.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

class Utils {

   companion object{

   }
}

fun LifecycleOwner.launchAndRepeatOn(
    state: Lifecycle.State,
    block: suspend CoroutineScope.() -> Unit
) {
    lifecycleScope.launch { repeatOnLifecycle(state, block) }
}

fun <T> Flow<T>.collectWhenStarted(
    owner: LifecycleOwner,
    action: FlowCollector<T>
) {
    owner.launchAndRepeatOn(Lifecycle.State.STARTED) { collect(action) }
}