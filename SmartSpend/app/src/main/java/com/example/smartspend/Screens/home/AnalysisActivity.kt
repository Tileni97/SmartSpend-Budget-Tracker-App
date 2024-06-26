package com.example.smartspend.Screens.home

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.smartspend.data.AnalysisRepository
import com.example.smartspend.data.Categories
import com.example.smartspend.data.CategoryRepository
import com.example.smartspend.data.TransectionRepository
import com.example.smartspend.data.UserRepository
import com.example.smartspend.navigation.Routes
import com.example.smartspend.setAnalysis
import com.example.smartspend.setCategory
import com.example.smartspend.setTransection
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun AnalysisActivity(navController: NavHostController) {

    var userEmail: String = UserRepository.getEmail()

    setTransection(userEmail)

    val context = LocalContext.current

    setCategory(UserRepository.getEmail(),context)
    val chartDataList = setAnalysis(TransectionRepository.getTransection(),CategoryRepository.getAllCategories(), context).toMutableList()

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            AnalysisTopBar(navController)
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 10.dp),
                contentAlignment = Alignment.Center,
            ){
                Column (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Text(text = "Track your spending for this month",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.W700,
                        color = Color(0xff009177),
                        textAlign = TextAlign.Center)

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

            PieChartWithLabels(chartDataList)
        }
    }


}

@Composable
fun PieChartWithLabels(chartDataList: MutableList<Categories>) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var total = AnalysisRepository.getOverAllPercentage().toInt()

        Column (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){


            Text(text = "${total.toInt()}%",
                fontSize = 50.sp,
                fontWeight = FontWeight.W700,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {


            Canvas(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(.7f)
                    .aspectRatio(1f)
            ) {
                val width = size.width
                val radius = width / 2f
                val strokeWidth = 20.dp.toPx()

                var startAngle = -90f

                for (index in 0..chartDataList.lastIndex) {
                    val chartData = chartDataList[index]
                    val sweepAngle = (chartData.amount?.toFloat() ?: 0f) / 180f * 360f
                    val angleInRadians = (startAngle + sweepAngle / 2).degreeToAngle

                    chartData.color?.let {
                        drawArc(
                            color = it,
                            startAngle = startAngle,
                            sweepAngle = sweepAngle,
                            useCenter = false,
                            topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
                            size = Size(width - strokeWidth, width - strokeWidth),
                            style = Stroke(strokeWidth)
                        )
                    }

                    val rectWidth = 20.dp.toPx()
                    chartData.color?.let {
                        drawRect(
                            color = it,
                            size = Size(rectWidth, rectWidth),
                            topLeft = Offset(
                                (-rectWidth / 2 + center.x + (radius + strokeWidth) * cos(angleInRadians)).toFloat(),
                                (-rectWidth / 2 + center.y + (radius + strokeWidth) * sin(angleInRadians)).toFloat()
                            )
                        )
                    }

                    drawContext.canvas.nativeCanvas.apply {
                        drawText(
                            "${chartData.amount?.toInt()}%",
                            (-rectWidth / 2 + center.x + (radius + strokeWidth + 10.dp.toPx()) * cos(angleInRadians)).toFloat(),
                            (-rectWidth / 2 + center.y + (radius + strokeWidth) * sin(angleInRadians)).toFloat(),
                            Paint().apply {
                                textSize = 25.sp.toPx()
                                color = android.graphics.Color.DKGRAY
                            }
                        )
                    }

                    startAngle += sweepAngle
                }
            }
        }

        Spacer(modifier = Modifier.height(26.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            ) {
            chartDataList.forEach { chartData ->
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    chartData.color?.let {
                        Modifier
                            .width(16.dp)
                            .height(16.dp)
                            .background(it)
                    }?.let {
                        Box(
                            modifier = it
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Row (
                        modifier =Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(text = chartData.name?:"",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.W700
                        )
                        Text(text = "${chartData.amount?.toInt()}%",
                            fontSize = 20.sp,
                            color = Color(0xff009177),
                            fontWeight = FontWeight.ExtraBold
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun AnalysisTopBar(navController: NavHostController){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xff009177)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ){
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 5.dp))
                    .padding(5.dp)
                    .clickable { navController.popBackStack() }

            ){
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "", tint = Color.White)
            }
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White
                )
            }
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 5.dp))
                    //.background(color = MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(5.dp)
                    .clickable {navController.navigate(Routes.HelpScreen.routes)}

            ){
                Icon(imageVector = Icons.Outlined.Info, contentDescription = "",tint = Color.White)
            }
        }
    }
}



private val Float.degreeToAngle
    get() = (this * Math.PI / 180f).toFloat()

@Immutable
data class ChartData(val label: String, val color: Color, val data: Float)