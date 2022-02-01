package com.ashish.custometimer.ui.start

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ashish.custometimer.model.CustomeTask
import com.ashish.custometimer.ui.main.MainViewModel
import com.ashish.custometimer.utils.calculateTime

@Composable
fun StartScreen(viewModel: MainViewModel, navController: NavController , id : Int) {


    Scaffold(
        topBar = { },
        content = {

        },
        modifier = Modifier.background(color = MaterialTheme.colors.background)
    )


}

@Composable
fun StartContent(customeTask: CustomeTask, navController: NavController) {

    val task: CustomeTask

    val timeUnit = "sec"
    val totalTime = calculateTime(customeTask.instructions)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = customeTask.title, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {

            Text(text = totalTime.toString(), fontSize = 26.sp, fontWeight = FontWeight.SemiBold)
            Text(text = timeUnit, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)

        }

        Canvas(modifier = Modifier.size(200.dp)){
            drawCircle(color = Color.Blue , radius = 100f)
        }


    }

}
