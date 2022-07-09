package com.debduttapanda.jetpackcomposecoil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.layout.onGloballyPositioned
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
                    var radius by remember { mutableStateOf(130f) }
                    var endAngle by remember { mutableStateOf(360f) }
                    var stepAngle by remember { mutableStateOf(0.01f) }
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
                            val end = endAngle
                            var v = 0f
                            val d = stepAngle
                            val points1 = mutableListOf<Offset>()
                            val points2 = mutableListOf<Offset>()
                            val points3 = mutableListOf<Offset>()
                            while(v<=end){
                                v += d
                                val j = v* PI/180f
                                val r1 = radius*f1(j)
                                points1.add(
                                    Offset(
                                        r1.toFloat()*cos(j).toFloat()+size.width/2f,
                                        r1.toFloat()*sin(j).toFloat()+size.height/2f
                                    )
                                )

                                val r2 = radius*f2(j)
                                points2.add(
                                    Offset(
                                        r2.toFloat()*cos(j).toFloat()+size.width/2f,
                                        r2.toFloat()*sin(j).toFloat()+size.height/2f
                                    )
                                )

                                val r3 = radius*f3(j)
                                points3.add(
                                    Offset(
                                        r3.toFloat()*cos(j).toFloat()+size.width/2f,
                                        r3.toFloat()*sin(j).toFloat()+size.height/2f
                                    )
                                )
                            }
                            drawPoints(
                                points = points1,
                                pointMode = PointMode.Lines,
                                color = Color(0xff03fcd7),
                                strokeWidth = 8f
                            )
                            drawPoints(
                                points = points2,
                                pointMode = PointMode.Lines,
                                color = Color(0xff03fc4e),
                                strokeWidth = 8f
                            )
                            drawPoints(
                                points = points3,
                                pointMode = PointMode.Lines,
                                color = Color(0xfffca103),
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
                                valueRange = 0f..360*1f,
                                onValueChange = {
                                    endAngle = it
                                }
                            )
                            Slider(
                                value = stepAngle,
                                valueRange = 0.01f..1f,
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
val π : Double
get() = PI

fun f1(Θ: Double): Double{
    return 1+(((abs(cos(Θ*3)))+(0.25-(abs(cos(3*Θ+ π/2))))*2)/(2+abs(cos(6*Θ+π/2))*8))
}
fun f2(Θ: Double): Double{
    return 2+(((abs(cos(Θ*3)))+(0.25-(abs(cos(3*Θ+ π/2))))*2)/(2+abs(cos(6*Θ+π/2))*8))
}
fun f3(Θ: Double): Double{
    return 3+(((abs(cos(Θ*6)))+(0.25-(abs(cos(6*Θ+ π/2))))*2)/(2+abs(cos(12*Θ+π/2))*8))
}