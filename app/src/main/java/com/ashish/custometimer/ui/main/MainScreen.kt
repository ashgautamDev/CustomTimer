package com.ashish.custometimer.ui.main

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ashish.custometimer.model.CustomeTask
import com.ashish.custometimer.navigation.Screens
import com.ashish.custometimer.ui.component.AppFab
import com.ashish.custometimer.ui.component.EmptyScreen
import com.ashish.custometimer.ui.component.TopAppBar
import com.ashish.custometimer.utils.ViewState

@ExperimentalFoundationApi
@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavController) {

    Scaffold(modifier = Modifier.padding(16.dp),
        topBar = {
            TopAppBar()
        },
        floatingActionButton = {
            AppFab() {
                navController.navigate(Screens.Add.route)
            }

        },
        content = {

            CustomeTaskContent(viewModel, navController)

        }
    )

}

@ExperimentalFoundationApi
@Composable
fun CustomeTaskContent(viewModel: MainViewModel, navController: NavController) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val context = LocalContext.current

        //Observe Tasks
        when (val result = viewModel.uiState.collectAsState().value) {
            is ViewState.Empty -> {
                EmptyScreen(navController)
            }
            is ViewState.Error -> {
                Toast.makeText(
                    context,
                    "Error ${result.exception}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is ViewState.Success -> {
                CustomeTasksList(result.task, navController)
            }
            is ViewState.Loading -> {
                Toast.makeText(
                    context,
                    "Task is Loading Please Wait duh ",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun CustomeTasksList(task: List<CustomeTask>, navController: NavController) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 16.dp)
    ) {
        items(task) { item ->
            TaskCard(task = item, navController)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }


}

@Composable
fun TaskCard(task: CustomeTask, navController: NavController) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .size(80.dp, 120.dp)
            .background(shape = RoundedCornerShape(16.dp), color = Color(0x3A2196F3))
            .clickable {
                navController.navigate("${Screens.Start.route}/${task.pid.toString()}")
            },
        contentAlignment = Alignment.TopStart
    ) {
        Text(
            text = task.title,
            color = MaterialTheme.colors.onSurface,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp),
            softWrap = true
        )


    }

}
