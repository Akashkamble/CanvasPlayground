package com.akash.canvasplayground.beesandbombs

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import com.akash.canvasplayground.utils.animationTimeMillis

/**
 * Created by Akash on 16/10/20
 */
@Composable
fun RotatingSquares(modifier: Modifier) {

    val animatedProgress = animationTimeMillis()
    val n = 60
    val rectSize = 20f
    val rectList = mutableListOf<Rect>()
    val colorList = listOf(
        Color(red = 40, green = 223, blue = 153),
        Color(red = 210, green = 246, blue = 197),
        Color(red = 246, green = 247, blue = 212)
    )
    for (i in 0..n) {
        val s = rectSize * i
        val offset = Offset(-s / 2, -s / 2)
        val color = colorList[i % 3]
        val rect = Rect(s, offset, color)
        rectList.add(rect)
    }
    Canvas(modifier = modifier.clipToBounds()) {
        translate(size.width / 2, size.height / 2) {
            val angle = (animatedProgress.value) * 0.0001f * 360f
            for (i in n downTo 0) {
                rotate((angle * (n - i + 1) * 0.05f), Offset(0f, 0f)) {
                    drawRect(
                        SolidColor(rectList[i].color),
                        rectList[i].offset,
                        Size(rectList[i].size, rectList[i].size),
                        alpha = 0.7f
                    )
                }
            }
        }
    }
}

data class Rect(
    val size: Float,
    val offset: Offset,
    val color: Color
)