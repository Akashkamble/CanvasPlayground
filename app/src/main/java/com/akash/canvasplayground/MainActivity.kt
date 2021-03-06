package com.akash.canvasplayground

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
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
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize(),
                            ) {
                                CanvasContainer(canvas = canvas)
                                Spacer(
                                    modifier = Modifier.preferredHeightIn(
                                        min = 20.dp,
                                        max = 40.dp
                                    )
                                )
                                Text(
                                    "View more animations",
                                    modifier = Modifier.clickable(onClick = {
                                        drawerState.open()
                                    })
                                )
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
    Surface(
        modifier = modifier
            .preferredHeight(400.dp)
            .preferredWidth(400.dp),
        border = BorderStroke(width = 2.dp, Color.Black)
    ) {
        CanvasRepository.getComposable(canvas)
    }
}

