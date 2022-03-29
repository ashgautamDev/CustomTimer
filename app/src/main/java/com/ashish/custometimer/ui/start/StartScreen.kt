package com.ashish.custometimer.ui.start

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ashish.custometimer.model.CustomeTask
import com.ashish.custometimer.navigation.Screens
import com.ashish.custometimer.ui.component.EmptyScreen
import com.ashish.custometimer.ui.main.CustomeTasksList
import com.ashish.custometimer.ui.main.MainViewModel
import com.ashish.custometimer.ui.theme.MaximumBlue
import com.ashish.custometimer.utils.TaskState
import com.ashish.custometimer.utils.ViewState
import com.ashish.custometimer.utils.calculateTime

@Composable
fun StartScreen(viewModel: MainViewModel, navController: NavHostController, id : String) {

    val context = LocalContext.current
    viewModel.getCustomeTask(id.toLong())
    Scaffold(
        content = {
            //Observe Tasks
            when (val result = viewModel.taskState.collectAsState().value) {
                is TaskState.Empty -> {
                    EmptyScreen(navController)
                }
                is TaskState.Error -> {
                    Toast.makeText(
                        context,
                        "Error ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is TaskState.Success -> {
                   StartContent(customeTask = result.task) {
                       navController.navigate("${Screens.Timer.route}/$id")
                   }
                }
                is TaskState.Loading -> {

                }
            }


        },
        modifier = Modifier.background(color = MaximumBlue)
    )


}

@Composable
fun StartContent(customeTask: CustomeTask  , onStartClick : ()-> Unit) {

    val timeUnit = "sec"
    val totalTime = calculateTime(customeTask)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = customeTask.title, fontSize = 36.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(40.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp) , verticalAlignment = Alignment.Bottom) {

            Text(text = "Total Time $totalTime ", fontSize = 26.sp, fontWeight = FontWeight.SemiBold)
            Text(text = timeUnit, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)

        }
        Spacer(modifier = Modifier.height(20.dp))




        Box(
            modifier = Modifier
                .size(250.dp)
                .clickable {
                    onStartClick()
                }
                .shadow(elevation = 0.dp, shape = CircleShape)
                .border(
                    border = BorderStroke(6.dp, color = MaterialTheme.colors.primary),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(text = "Start", fontSize = 48.sp, fontWeight = FontWeight.ExtraBold , color = MaterialTheme.colors.primary)

        }


    }

}
