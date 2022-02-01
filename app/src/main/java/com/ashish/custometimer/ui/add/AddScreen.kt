package com.ashish.custometimer.ui.add

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ashish.custometimer.R
import com.ashish.custometimer.model.CustomeTask
import com.ashish.custometimer.model.Instruction
import com.ashish.custometimer.model.Task
import com.ashish.custometimer.navigation.Screens
import com.ashish.custometimer.ui.main.MainViewModel

@Composable
fun AddScreen(viewModel: MainViewModel, navController: NavController) {

    val context = LocalContext.current
    var title by remember {
        mutableStateOf("")
    }

    val tasks = mutableListOf<Task>()

    var instructTitle by remember {
        mutableStateOf("")
    }
    var time by remember {
        mutableStateOf(10)
    }
    Column(modifier = Modifier.fillMaxSize().padding(32.dp), verticalArrangement = Arrangement.spacedBy(20.dp) ) {
        //topApp Bar
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "",
                modifier = Modifier
                    .rotate(45f)
                    .clickable {
                        Toast
                            .makeText(context, "You will lose current entry", Toast.LENGTH_SHORT)
                            .show()
                        navController.navigate(Screens.Main.route)
                    }
            )
            Text(
                text = stringResource(R.string.add_screen_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "",
                modifier = Modifier.clickable {
                    Toast
                        .makeText(context, "You have added Task", Toast.LENGTH_SHORT)
                        .show()
                    viewModel.insertCustomeTask(
                        CustomeTask(
                            title = title,
                            instructions = Instruction(tasks)
                        )
                    )
                    navController.navigate(Screens.Main.route)
                }
            )

        }
        //Title Text Box
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            textStyle = TextStyle(textAlign = TextAlign.Start),
            onValueChange = { title = it },
            shape = RoundedCornerShape(16.dp),
            singleLine = true

        )
        TaskListContainer(tasks)

        Row(modifier = Modifier.fillMaxWidth()) {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(4f),
                value = instructTitle,
                textStyle = TextStyle(textAlign = TextAlign.Start),
                onValueChange = { instructTitle = it },
                shape = RoundedCornerShape(16.dp),
                singleLine = true
            )
            Spacer(modifier = Modifier.width(16.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = instructTitle,
                textStyle = TextStyle(textAlign = TextAlign.Start),
                onValueChange = { instructTitle = it },
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,

                    ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        tasks.add(Task(instructTitle, time))
                        instructTitle = ""
                        time = 10
                    }
                )
            )


        }

    }


}

@Composable
fun InstructionAdder() {

    var instructTitle by remember {
        mutableStateOf("")
    }
    var time by remember {
        mutableStateOf(10)
    }
    val entry = instructTitle != null && time != null
    Row(modifier = Modifier.fillMaxWidth()) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(4f),
            value = instructTitle,
            textStyle = TextStyle(textAlign = TextAlign.Start),
            onValueChange = { instructTitle = it },
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,

                )
        )
        Spacer(modifier = Modifier.width(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = instructTitle,
            textStyle = TextStyle(textAlign = TextAlign.Start),
            onValueChange = { instructTitle = it },
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done.also { },

                )
        )


    }
}

@Composable
fun TaskListContainer(tasks: List<Task>) {

    LazyColumn(
        modifier = Modifier.padding(
            32.dp
        )
    ) {

        items(tasks) { it ->
            TaskCardBasic(it)
        }


    }

}

@Composable
fun TaskCardBasic(task: Task) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = task.title,
            color = MaterialTheme.colors.primary.copy(alpha = .7f),
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = task.time.toString(),
            color = Color.Green,
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold
        )
    }

}


