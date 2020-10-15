package com.akash.canvasplayground.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Akash on 15/10/20
 */
class CanvasViewModel : ViewModel() {
    val canvasEvent = MutableLiveData<Canvas>()
    fun navigateTo(canvas: Canvas) {
        canvasEvent.value = canvas
    }

    sealed class Canvas {
        object CircleAndDotWave : Canvas()
        object CirCleGridScale : Canvas()
    }
}