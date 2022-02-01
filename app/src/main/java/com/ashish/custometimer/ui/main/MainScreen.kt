package com.ashish.custometimer.ui.main

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ashish.custometimer.model.CustomeTask
import com.ashish.custometimer.ui.component.AppFab
import com.ashish.custometimer.ui.component.EmptyScreen
import com.ashish.custometimer.utils.ViewState

@ExperimentalFoundationApi
@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavController) {

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Custome Tasks ",
                    fontSize = 28.sp,
                    color = Color.Blue,
                    fontWeight = FontWeight.SemiBold
                )

                Image(imageVector = Icons.Default.Lightbulb, contentDescription = null)

            }
        },
        floatingActionButton = {
AppFab() {

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
                CustomeTasksList(result.task)
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
fun CustomeTasksList(task: List<CustomeTask>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ) {
        items(task) { item ->
            TaskCard(task = item)
            Spacer(modifier = Modifier.heightIn(10.dp))
        }
    }


//    LazyColumn(
//        modifier = Modifier.padding(
//            start = 46.dp,
//            top = 12.dp,
//            end = 0.dp,
//            bottom = 48.dp
//        )
//    ) {
//
//        items(task) { item ->
//            TaskCard(task = item)
//            Spacer(modifier = Modifier.heightIn(10.dp))
//        }
//
//    }
}

@Composable
fun TaskCard(task: CustomeTask) {
    Card(
        modifier = Modifier.shadow(
            elevation = 6.dp,
            shape = RoundedCornerShape(16.dp),
            clip = true
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = task.title,
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp),
                softWrap = true
            )


        }

    }

}
