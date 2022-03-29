package com.ashish.custometimer.ui.add

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Title
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.ColorFilter
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
import com.ashish.custometimer.ui.component.TimePickerDialog
import com.ashish.custometimer.ui.main.MainViewModel
import com.ashish.custometimer.utils.calculateSec
import com.ashish.custometimer.utils.statusText

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddScreen(
    mainViewModel: MainViewModel,
    addViewModel: AddViewModel,
    navController: NavController
) {

    val context = LocalContext.current

    var title by remember {
        mutableStateOf("")
    }
    var instructTitle by remember {
        mutableStateOf("")
    }
    var minute by remember {
        mutableStateOf(0)
    }
    var sec by remember {
        mutableStateOf(0)
    }
    val timePicked = addViewModel.time.observeAsState()
    val tasks = remember {
        mutableStateListOf<Task>()
    }
    val focusManager = LocalFocusManager.current
    var instructionMessage = statusText(title, tasks, Task(instructTitle, sec.toString()))
    if (tasks.size > 0) instructionMessage = "Add more instructions"

    val showDialogState: Boolean = addViewModel.showDialog.collectAsState().value
    TimePickerDialog(
        open = showDialogState,
        min = {
            minute = it
        },
        sec = {
            sec = it

        },
        onConfirm = { addViewModel.onDialogConfirm() },
        onDismiss = { addViewModel.onDialogDismiss() })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AddTopBar(
            context = context,
            onCrossClick = { navController.navigate(Screens.Main.route) }) {

            if (tasks.size != 0) {
                if (title.isNotBlank()) {
                    mainViewModel.insertCustomeTask(
                        CustomeTask(
                            title = title,
                            instructTasks = tasks
                        )
                    )
                    navController.navigate(Screens.Main.route)
                } else {
                    Toast.makeText(context, "Please Add Title for Tasks ", Toast.LENGTH_SHORT)
                        .show()
                }

            } else {
                Toast.makeText(context, "Please Add Subtask", Toast.LENGTH_SHORT).show()
            }


        }

        //Title Text Box
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
                .onPreviewKeyEvent {
                    if (it.key.keyCode == Key.Tab.keyCode) {
                        focusManager.moveFocus(FocusDirection.Down)
                        true
                    } else {
                        false
                    }
                },
            value = title,
            leadingIcon = {
                Image(
                    imageVector = Icons.Default.Title,
                    contentDescription = "",
                    modifier = Modifier.size(32.dp)
                )

            },
            textStyle = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                textDirection = TextDirection.Content
            ),
            onValueChange = { title = it },
            singleLine = true,
            placeholder = {
                Text(
                    color = MaterialTheme.colors.background.copy(alpha = .5f),
                    text = stringResource(R.string.title_place_holder),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )

            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,

                ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )


        )
        Divider(
            modifier = Modifier.background(
                color = MaterialTheme.colors.onSecondary.copy(
                    alpha = .8f
                )
            )
        )
        TaskListContainer(tasks, modifier = Modifier.weight(5F), title)


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = instructionMessage, fontSize = 18.sp, fontWeight = FontWeight.Light)
            if (instructTitle.isNotBlank() && sec != 0) {
                Image(imageVector = Icons.Default.Done,
                    contentDescription = "",
                    modifier = Modifier
                        .weight(1F)
                        .clickable {
                            tasks.add(Task(instructTitle, calculateSec(minute, sec)))
                            instructTitle = ""
                            sec = 0
                            minute = 0

                        })

            }
        }

        Row(
            modifier = Modifier
                .padding(2.dp)
                .height(58.dp)
                .background(
                    color = MaterialTheme.colors.surface,
                    shape = MaterialTheme.shapes.small
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                colorFilter = ColorFilter.tint(color = MaterialTheme.colors.background),
                imageVector = Icons.Outlined.Circle,
                contentDescription = "",
                modifier = Modifier.weight(1F)
            )
            BasicTextField(
                value = instructTitle,
                onValueChange = { instructTitle = it },
                modifier = Modifier.weight(4F),
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = MaterialTheme.colors.onSurface
                ),
                singleLine = true, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                ), keyboardActions = KeyboardActions()
            )
            if (!(instructTitle.isBlank())) {
                Image(colorFilter = ColorFilter.tint(color = MaterialTheme.colors.background),
                    imageVector = Icons.Default.Timer,
                    contentDescription = "",
                    modifier = Modifier
                        .size(32.dp)
                        .weight(1F)
                        .clickable {
                            addViewModel.onOpenDialogClicked()
                        })

            }
        }

    }
}

@Composable
fun TaskListContainer(tasks: List<Task>, modifier: Modifier, title: String) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(
                color = MaterialTheme.colors.secondary.copy(alpha = 0.2f),
                shape = MaterialTheme.shapes.small
            )
    ) {
        LazyColumn(
            modifier = Modifier.padding(
                16.dp
            )
        ) {
            item {
                Text(text = if (title.isNotEmpty()) "$title instructions" else "Your instructions")
            }
            items(tasks) { it ->
                TaskCardBasic(it)
            }


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

