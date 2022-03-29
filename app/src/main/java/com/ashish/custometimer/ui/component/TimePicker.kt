package com.ashish.custometimer.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun TimePickerDialog(open: Boolean, min: (Int) -> Unit, sec: (Int) -> Unit , onDismiss : () -> Unit,  onConfirm : () -> Unit, ) {
    if (open) AlertDialog(
        onDismissRequest = onDismiss ,
        confirmButton = {
            TextButton(onClick = onConfirm)
            { Text(text = "OK" , fontSize = 24.sp , fontWeight = FontWeight.Bold) }
        } ,
        dismissButton = {
            TextButton(onClick = onDismiss)
            { Text(text = "Cancel" , fontSize = 24.sp , fontWeight = FontWeight.Bold) }
        },
        text = { TimePicker(min = { min(it) }) { sec(it) } } ,
        backgroundColor = MaterialTheme.colors.secondary
    )
}


@Composable
fun TimePicker(min: (Int) -> Unit, sec: (Int) -> Unit) {

    Column( horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Set Time", fontWeight = FontWeight.SemiBold, fontSize = 24.sp)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly , verticalAlignment = Alignment.CenterVertically) {
            Picker(maxNumber = 90, updatedValue = {
                min(it)
            })
            Text(text = ":" , fontSize = 28.sp , fontWeight = FontWeight.Bold)
            Picker(maxNumber = 60, updatedValue = {
                sec(it)
            })
        }
    }

}


@Composable
fun Picker(maxNumber: Int, updatedValue: (Int) -> Unit) {

    var number by remember {
        mutableStateOf(0)
    }

    if (number > maxNumber) number = maxNumber
    updatedValue(number)
    Column( modifier = Modifier.width(40.dp) ,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        IconButton(onClick = { number += 15 }) {
            Icon(
                imageVector = Icons.Default.ArrowDropUp,
                contentDescription = "",
                Modifier.size(24.dp)
            )
        }

        BasicTextField(
            value = number.toString(),
            onValueChange = { number = it.toInt() },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            textStyle = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold , textAlign = TextAlign.Center)
        )

        IconButton(onClick = { if (number != 0) number -= 15 }) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "",
                Modifier.size(24.dp)
            )
        }
    }

}

