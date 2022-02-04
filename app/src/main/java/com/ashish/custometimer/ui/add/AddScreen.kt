package com.ashish.custometimer.ui.add

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Title
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ashish.custometimer.R
import com.ashish.custometimer.model.CustomeTask
import com.ashish.custometimer.model.Task
import com.ashish.custometimer.navigation.Screens
import com.ashish.custometimer.ui.component.AddTopBar
import com.ashish.custometimer.ui.main.MainViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddScreen(viewModel: MainViewModel, navController: NavController) {

    val context = LocalContext.current
    var title by remember {
        mutableStateOf("")
    }
    var instructTitle by remember {
        mutableStateOf("")
    }
    var time by remember {
        mutableStateOf("")
    }
    val tasks = remember {
        mutableStateListOf<Task>()
    }
    val focusManager = LocalFocusManager.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        //topApp Bar
        AddTopBar(
            context = context,
            onCrossClick = { navController.navigate(Screens.Main.route) }) {

            if ( tasks.size != 0) {
                if(title.isNotBlank()){
                viewModel.insertCustomeTask(
                    CustomeTask(
                        title = title,
                        tasks = tasks
                    )
                )
                navController.navigate(Screens.Main.route)}
                else {
                    Toast.makeText(context, "Please Add Title for Tasks ", Toast.LENGTH_SHORT).show()
                }

            }else {
                Toast.makeText(context, "Please Add Subtask", Toast.LENGTH_SHORT).show()
            }



        }

        //Title Text Box
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onPreviewKeyEvent {
                    if (it.key.keyCode == Key.Tab.keyCode){
                        focusManager.moveFocus(FocusDirection.Down)
                        true
                    } else {
                        false
                    }
                },
            value = title,
            leadingIcon = {
                Image(imageVector = Icons.Default.Title, contentDescription = "")

            },
            textStyle = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                textDirection = TextDirection.Content,
                textDecoration = TextDecoration.Underline
            ),
            onValueChange = { title = it },
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            placeholder = {
                Text(
                    text = stringResource(R.string.add_screen_title_place_holder),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )

            } ,  keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,

                ) ,
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )


        )
        TaskListContainer(tasks)


        //Instruction
        Row(modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.SpaceBetween) {

            OutlinedTextField(
                modifier = Modifier
                    .onPreviewKeyEvent {
                        if (it.key == Key.Tab){
                            focusManager.moveFocus(FocusDirection.Down)
                            true
                        } else {
                            false
                        }
                    }
                    .width(250.dp)
                    .height(58.dp)
                    .focusOrder { FocusDirection.Next },
                value = instructTitle,
                leadingIcon = {
                    Image(imageVector = Icons.Outlined.Circle, contentDescription = "")

                },
                textStyle = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                ),
                onValueChange = { instructTitle = it },
                shape = RoundedCornerShape(32.dp),
                singleLine = true, placeholder = {
                    Text(
                        text = stringResource(R.string.add_screen_title_place_holder),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                }
            ,  keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,

                    ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        if (subTaskEmptyCondition(instructTitle , time)) {
                            tasks.add(Task(instructTitle, time))
                            instructTitle = ""
                            time = ""
                        }

                        focusManager.moveFocus(FocusDirection.Next)


                    }
                ,
                )
            )
            Spacer(modifier = Modifier.width(16.dp))

            OutlinedTextField(
                modifier = Modifier
                    .onPreviewKeyEvent {
                        if (it.key == Key.Tab){
                            focusManager.moveFocus(FocusDirection.Down)
                            true
                        } else {
                            false
                        }
                    }
                    .size(58.dp),
                value = time,
                textStyle = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                ),
                onValueChange = { time = it },
                placeholder = {
                    Image(imageVector = Icons.Default.Timer, contentDescription = "")
                },
                shape = RoundedCornerShape(32.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,

                    ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (subTaskEmptyCondition(instructTitle , time)) {
                            tasks.add(Task(instructTitle, time))
                            instructTitle = ""
                            time = ""
                            focusManager.moveFocus(FocusDirection.Previous)
                        }else {
                            Toast.makeText(context, "Please Add both field", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            )


        }

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
            text = task.taskTitle,
            color = MaterialTheme.colors.primary.copy(alpha = .7f),
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            softWrap = true,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = task.taskTime,
            color = MaterialTheme.colors.primary.copy(alpha = 1f),
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold
        )
    }

}
fun subTaskEmptyCondition(title : String , time : String) : Boolean
{
    return !(title.isBlank() || time.isBlank() || time.contentEquals(Int.toString()))
}

