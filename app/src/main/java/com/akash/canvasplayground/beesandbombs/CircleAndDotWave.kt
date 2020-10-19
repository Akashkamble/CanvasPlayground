package com.akash.canvasplayground.beesandbombs

import androidx.compose.animation.core.*
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform

/**
 * Created by Akash on 15/10/20
 * Credits : https://twitter.com/concinnus/status/1307831946563604480?s=20
 */

@Composable
fun CircleAndDotGrid(modifier: Modifier) {


    val rotation = FloatPropKey()
    val definition = transitionDefinition<Float> {
        state(0f) { this[rotation] = 0f }
        state(1f) { this[rotation] = 360f }
        transition {
            rotation using repeatable(
                iterations = AnimationConstants.Infinite,
                animation = tween(2200, 0, LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        }
    }

    val state = transition(
        definition = definition, initState = 0f, toState = 1f
    )

    Canvas(modifier = modifier.clipToBounds()) {
        val n = 25
        val circleRadius = (size.width / n + n / 3)
        val spacing = circleRadius + n / 3
        val dotRadius = circleRadius / 7
        val diff = spacing - circleRadius
        var offsetX = 0f
        var offsetY = 0f
        for (row in 0..n) {
            for (col in 0..n) {
                drawCircle(
                    SolidColor(Color.Black),
                    radius = circleRadius,
                    center = Offset(offsetX, offsetY),
                    style = Stroke(width = 3f)
                )
                withTransform({
                    rotate(
                        (state[rotation] + (row * col) + (col + row) * 15) % 360,
                        Offset(offsetX, offsetY - diff - circleRadius)
                    )
                }) {
                    drawCircle(
                        color = Color.Black,
                        radius = dotRadius,
                        center = Offset(offsetX - diff, offsetY - diff)
                    )
                }
                offsetX += spacing
            }
            offsetX = 0f
            offsetY += spacing
        }
    }
}