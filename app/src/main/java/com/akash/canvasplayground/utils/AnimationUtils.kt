package com.akash.canvasplayground.utils

import androidx.compose.runtime.*
import androidx.compose.runtime.dispatch.withFrameMillis
import androidx.compose.ui.platform.LifecycleOwnerAmbient
import androidx.lifecycle.whenStarted

/**
 * Created by Akash on 16/10/20
 * Copied From Alex LockWoods Compose bees-and-bombs compose samples
 * (https://github.com/alexjlockwood/bees-and-bombs-compose)
 * Returns a [State] holding a local animation time in milliseconds. The value always starts
 * at `0L` and stops updating when the call leaves the composition.
 */
@Composable
fun animationTimeMillis(): State<Long> {
    val millisState = mutableStateOf(0L)
    val lifecycleOwner = LifecycleOwnerAmbient.current
    LaunchedTask {
        val startTime = withFrameMillis { it }
        lifecycleOwner.whenStarted {
            while (true) {
                withFrameMillis { frameTime ->
                    millisState.value = frameTime - startTime
                }
            }
        }
    }
    return millisState
}