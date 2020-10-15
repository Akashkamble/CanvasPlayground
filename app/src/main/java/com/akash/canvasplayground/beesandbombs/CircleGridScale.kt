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
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke

/**
 * Created by Akash on 15/10/20
 */

@Composable
fun CircleGridScaleAnimation(modifier: Modifier) {
    val scale = FloatPropKey()
    val scaleDefinition = transitionDefinition<Float> {
        state(1f) { this[scale] = 0.5f }
        state(2f) { this[scale] = 1.75f }
        transition {
            scale using repeatable(
                iterations = AnimationConstants.Infinite,
                animation = tween(1500, 0, FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        }
    }
    val scaleState = transition(
        definition = scaleDefinition, initState = 1f, toState = 2f
    )

    Canvas(modifier = modifier.clipToBounds()) {
        val radius = 50f
        val n = 25
        var colOffset: Float
        var rowOffset = 0f
        for (row in 1..n) {
            colOffset = if (row % 2 == 0) {
                radius
            } else {
                0f
            }
            for (col in 1..n) {
                drawCircle(
                    SolidColor(Color.Black),
                    radius = radius * scaleState[scale],
                    center = Offset(
                        colOffset,
                        rowOffset
                    ),
                    style = if (scaleState[scale] in 0.5f..0.95f) Fill else Stroke(width = 3f)
                )
                colOffset += 2 * radius
            }
            rowOffset += 2 * radius - 10f
        }
    }
}