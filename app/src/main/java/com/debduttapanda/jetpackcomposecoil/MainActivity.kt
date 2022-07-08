package com.debduttapanda.jetpackcomposecoil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.debduttapanda.jetpackcomposecoil.ui.theme.JetpackComposeCoilTheme
import java.lang.Math.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCoilTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    var radius by remember { mutableStateOf(10f) }
                    var endAngle by remember { mutableStateOf(720f) }
                    var stepAngle by remember { mutableStateOf(0.1f) }
                    var size by remember { mutableStateOf(IntSize.Zero) }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .onGloballyPositioned {
                                size = it.size
                            }
                    ){
                        Canvas(
                            modifier = Modifier.fillMaxSize()
                        ){
                            val points = mutableListOf<Offset>()

                            val r = radius
                            val end = endAngle
                            var v = 0f
                            val d = stepAngle
                            while(v<=end){
                                v += d
                                val j = v* PI/180f
                                points.add(
                                    Offset(
                                        r*j.toFloat()*cos(j).toFloat()+size.width/2f,
                                        r*j.toFloat()*sin(j).toFloat()+size.height/2f
                                    )
                                )
                            }
                            drawPoints(
                                points = points,
                                pointMode = PointMode.Lines,
                                color = Color(0xff00ff00),
                                strokeWidth = 8f
                            )
                        }
                        Column(
                            modifier = Modifier.align(Alignment.BottomCenter).padding(24.dp)
                        ){
                            Slider(
                                value = radius,
                                valueRange = 0f..400f,
                                onValueChange = {
                                    radius = it
                                }
                            )
                            Slider(
                                value = endAngle,
                                valueRange = 0f..360*10f,
                                onValueChange = {
                                    endAngle = it
                                }
                            )
                            Slider(
                                value = stepAngle,
                                valueRange = 0.01f..10f,
                                onValueChange = {
                                    stepAngle = it
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}