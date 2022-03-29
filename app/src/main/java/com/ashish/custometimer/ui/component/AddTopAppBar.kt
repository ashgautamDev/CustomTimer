package com.ashish.custometimer.ui.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashish.custometimer.R


@Composable
fun AddTopBar(context: Context, onCrossClick: () -> Unit, onCheckClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "",
            modifier = Modifier
                .rotate(45f)
                .size(28.dp)
                .clickable {
                    Toast
                        .makeText(context, "You will lose current entry", Toast.LENGTH_SHORT)
                        .show()
                    onCrossClick()
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
            modifier = Modifier
                .clickable {
                    Toast
                        .makeText(context, "You have added Task", Toast.LENGTH_SHORT)
                        .show()
                    onCheckClick()
                }
                .size(28.dp)
        )

    }
}




