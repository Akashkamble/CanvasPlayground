package com.akash.canvasplayground

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akash.canvasplayground.viewmodel.CanvasViewModel

/**
 * Created by Akash on 15/10/20
 */

@Composable
fun DrawerContent(onClick: (canvas: CanvasViewModel.Canvas) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        DrawerItem(
            name = "CircleAndDotWave",
            canvas = CanvasViewModel.Canvas.CircleAndDotWave,
            onClick = {
                onClick(it)
            }
        )
        DrawerItem(
            name = "CircleGridScale",
            canvas = CanvasViewModel.Canvas.CirCleGridScale,
            onClick = {
                onClick(it)
            }
        )
    }
}


@Composable
fun DrawerItem(
    name: String,
    canvas: CanvasViewModel.Canvas,
    onClick: (canvas: CanvasViewModel.Canvas) -> Unit
) {
    Text(
        text = name,
        modifier = Modifier.fillMaxWidth()
            .clickable(onClick = { onClick(canvas) }).padding(all = 15.dp)
    )
}