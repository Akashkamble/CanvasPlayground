package com.akash.canvasplayground

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Box
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import com.akash.canvasplayground.beesandbombs.CircleAndDotGrid
import com.akash.canvasplayground.beesandbombs.CircleGridScaleAnimation
import com.akash.canvasplayground.ui.CanvasPlaygroundTheme
import com.akash.canvasplayground.viewmodel.CanvasViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: CanvasViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CanvasPlaygroundTheme {
                val canvas =
                    viewModel.canvasEvent.observeAsState(CanvasViewModel.Canvas.CircleAndDotWave)
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                ModalDrawerLayout(
                    drawerState = drawerState,
                    drawerContent = {
                        DrawerContent(onClick = {
                            viewModel.navigateTo(it)
                            drawerState.close()
                        })
                    },
                    bodyContent = {
                        Surface(color = MaterialTheme.colors.background) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalGravity = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize(),
                            ) {
                                CanvasContainer(canvas = canvas)
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun CanvasContainer(modifier: Modifier = Modifier, canvas: State<CanvasViewModel.Canvas>) {
    Box(
        gravity = Alignment.Center,
        modifier = modifier
            .preferredHeight(400.dp)
            .preferredWidth(400.dp),
        border = BorderStroke(
            width = 2.dp,
            brush = SolidColor(Color.Black)
        )
    ) {
        when (canvas.value) {
            CanvasViewModel.Canvas.CircleAndDotWave -> CircleAndDotGrid(
                modifier = Modifier.fillMaxSize()
            )
            CanvasViewModel.Canvas.CirCleGridScale -> CircleGridScaleAnimation(
                Modifier.fillMaxSize()
            )
        }
    }
}