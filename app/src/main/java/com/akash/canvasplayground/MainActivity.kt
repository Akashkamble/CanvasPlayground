package com.akash.canvasplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Box
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import com.akash.canvasplayground.beesandbombs.CircleAndDotGrid
import com.akash.canvasplayground.ui.CanvasPlaygroundTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CanvasPlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalGravity = Alignment.CenterHorizontally
                    ) {
                        Box(
                            Modifier
                                .preferredHeight(400.dp)
                                .preferredWidth(400.dp),
                            border = BorderStroke(width = 2.dp, brush = SolidColor(Color.Black))
                        ) {
                            CircleAndDotGrid(Modifier.fillMaxSize())
                        }
                    }
                }
            }
        }
    }
}