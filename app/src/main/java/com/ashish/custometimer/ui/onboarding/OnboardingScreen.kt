package com.ashish.custometimer.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ashish.custometimer.R
import com.ashish.custometimer.navigation.Screens


@Composable
fun OnboardingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(id = R.string.app_name), fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground, fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Time to complete " +
                        "your tasks ", fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground, fontSize = 36.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Getting more clear task instruction " +
                        "you can complete easily ", fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.surface, fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
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
                    text = "Get started",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    color = Color.White, fontSize = 24.sp
                )


            }
        }

        Image(
            painter = painterResource(id = R.drawable.mount_image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

    }


}



