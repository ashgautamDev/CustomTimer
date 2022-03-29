package com.ashish.custometimer.ui

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.ashish.custometimer.R
import com.ashish.custometimer.model.CustomeTask
import com.ashish.custometimer.navigation.Screens
import com.ashish.custometimer.ui.component.EmptyScreen
import com.ashish.custometimer.ui.main.MainViewModel
import com.ashish.custometimer.ui.theme.MaximumBlue
import com.ashish.custometimer.utils.TaskState
import kotlinx.coroutines.delay


@SuppressLint("UnrememberedMutableState")
@Composable
fun TimerScreen(viewModel: MainViewModel, navController: NavHostController, id: String) {
    val context = LocalContext.current

    viewModel.getCustomeTask(id.toLong())
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
         TimerContent(customeTask = result.task, context , navController)
        }
        is TaskState.Loading -> {
            Toast.makeText(
                context,
                "Task is Loading Please Wait duh ",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

@Composable
fun TimerContent(customeTask : CustomeTask , context : Context , navController: NavController) {

    val lastIndex = customeTask.instructTasks.lastIndex
//    val finished = customeTask.instructTasks[lastIndex].taskTime.toInt()
    var finished = remember {
        mutableStateOf(false)
    }
    val index by remember {
        mutableStateOf(0)
    }
    var number by remember {
        mutableStateOf(customeTask.instructTasks[index].taskTime.toInt())
    }
    var instTitle by remember {
        mutableStateOf(customeTask.instructTasks[index].taskTitle)
    }
    val sound : MediaPlayer = MediaPlayer.create(context , R.raw.whistle )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaximumBlue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LaunchedEffect(key1 = Unit) {
        try {
          for (tasks in customeTask.instructTasks){
              delay(2000L)
              instTitle = tasks.taskTitle
              for (num in tasks.taskTime.toInt() downTo 0){
                  delay(1000L)
                  number = num
              }
              sound.start()



          }
            finished.value = true
        } catch (ex: Exception) {
            println("timer cancelled")
        }

    }
        Text(
            text = instTitle,
            fontWeight = FontWeight.Bold,
            fontSize = 60.sp

        )
        Spacer(modifier = Modifier.height(32.dp))
        SubTaskTimer(number)
        Spacer(modifier = Modifier.height(32.dp))


        if (finished.value)   Button(
            onClick = { navController.navigate(Screens.Main.route){
                this.launchSingleTop
            } },
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = MaterialTheme.colors.primary,
                    shape = RoundedCornerShape(16.dp)
                )
        )
        {
            Text(
                text = "Back to Home",
                style = TextStyle(fontWeight = FontWeight.Bold),
                color = Color.White, fontSize = 24.sp
            )


        }


    }
}

@Composable
fun SubTaskTimer(taskTime: Int) {

    Box(
        modifier = Modifier
            .size(250.dp)
            .shadow(elevation = 0.dp, shape = CircleShape)
            .border(
                border = BorderStroke(6.dp, color = MaterialTheme.colors.primary),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(text = if(taskTime != 0) taskTime.toString() else "Finish", fontSize = 48.sp, fontWeight = FontWeight.ExtraBold , color = MaterialTheme.colors.primary)

    }

}


