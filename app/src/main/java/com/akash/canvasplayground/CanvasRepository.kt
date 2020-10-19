package com.akash.canvasplayground

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.akash.canvasplayground.beesandbombs.CircleAndDotGrid
import com.akash.canvasplayground.beesandbombs.CircleGridScaleAnimation
import com.akash.canvasplayground.beesandbombs.RotatingSquares
import com.akash.canvasplayground.viewmodel.CanvasViewModel

/**
 * Created by Akash on 16/10/20
 */
object CanvasRepository {
    fun getCanvasList(): List<DrawerItemData> {
        return listOf(
            DrawerItemData(
                "${CanvasViewModel.Canvas.CircleAndDotWave::class.simpleName}",
                canvas = CanvasViewModel.Canvas.CircleAndDotWave
            ),
            DrawerItemData(
                "${CanvasViewModel.Canvas.CircleGridScale::class.simpleName}",
                canvas = CanvasViewModel.Canvas.CircleGridScale
            ),
            DrawerItemData(
                "${CanvasViewModel.Canvas.RotatingSquares::class.simpleName}",
                canvas = CanvasViewModel.Canvas.RotatingSquares
            )
        )
    }

    @Composable
    fun getComposable(canvas: State<CanvasViewModel.Canvas>) {
        when (canvas.value) {
            CanvasViewModel.Canvas.CircleAndDotWave -> CircleAndDotGrid(
                modifier = Modifier.fillMaxSize()
            )
            CanvasViewModel.Canvas.CircleGridScale -> CircleGridScaleAnimation(
                modifier = Modifier.fillMaxSize()
            )
            CanvasViewModel.Canvas.RotatingSquares -> RotatingSquares(
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    data class DrawerItemData(val name: String, val canvas: CanvasViewModel.Canvas)
}