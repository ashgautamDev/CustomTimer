package com.ashish.custometimer.ui

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ashish.custometimer.R
import com.ashish.custometimer.model.CustomeTask
import com.ashish.custometimer.ui.component.EmptyScreen
import com.ashish.custometimer.ui.main.MainViewModel
import com.ashish.custometimer.utils.TaskState
import kotlinx.coroutines.delay


@SuppressLint("UnrememberedMutableState")
@Composable
fun TimerScreen(viewModel: MainViewModel, navController: NavHostController, id: String) {
    val context = LocalContext.current

    val mockTask= CustomeTask.mock()
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
         TimerContent(customeTask = result.task, context)
        }
        is TaskState.Loading -> {
            Toast.makeText(
                context,
                "Task is Loading Please Wait duh ",
                Toast.LENGTH_SHORT
            ).show()
        }
    }



    val scope = rememberCoroutineScope()

//TimerContent(customeTask = mockTask , context)


}

@Composable
fun TimerContent(customeTask : CustomeTask , context : Context) {
    val index by remember {
        mutableStateOf(0)
    }
    var number by remember {
        mutableStateOf(customeTask.tasks[index].taskTime.toInt())
    }
    var instTitle by remember {
        mutableStateOf(customeTask.tasks[index].taskTitle)
    }
    val sound : MediaPlayer = MediaPlayer.create(context , R.raw.whistle )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LaunchedEffect(key1 = Unit) {
        try {
          for (tasks in customeTask.tasks){
              delay(2000L)
              instTitle = tasks.taskTitle
              for (num in tasks.taskTime.toInt() downTo 0){
                  delay(1000L)
                  number = num
              }
              sound.start()

          }
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
        SubTaskTimer(number) {
        }


    }
}

@Composable
fun SubTaskTimer(taskTime: Int, onTimerEnd: () -> Unit) {

    Box(
        modifier = Modifier
            .size(250.dp)
            .shadow(elevation = 0.dp, shape = CircleShape)
            .border(
                border = BorderStroke(6.dp, color = MaterialTheme.colors.onBackground),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(text = taskTime.toString(), fontSize = 48.sp, fontWeight = FontWeight.ExtraBold)

    }

}

suspend fun startTimer(time: Long, onTimerEnd: () -> Unit) {
    delay(timeMillis = time)
    onTimerEnd()
}
