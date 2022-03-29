package com.ashish.custometimer.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddInstruction(onTitleValueChange : (String) -> Unit, onTimeSelection: () -> Unit, focusManager: FocusManager , modifier: Modifier = Modifier) {

    var text by remember {
        mutableStateOf(onTitleValueChange)
    }
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(horizontal = 16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//
//        Row(
//            modifier = Modifier
//                .weight(5f)
//                .height(58.dp)
//                .background(
//                    color = MaterialTheme.colors.surface,
//                    shape = MaterialTheme.shapes.small
//                ),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Image(
//                imageVector = Icons.Outlined.Circle,
//                contentDescription = "",
//                modifier = Modifier.weight(1F)
//            )
//            BasicTextField(
//                value = text,
//                onValueChange = {  },
//                modifier = Modifier.weight(5F),
//                textStyle = TextStyle(
//                    fontWeight = FontWeight.SemiBold,
//                    fontSize = 24.sp,
//                    color = MaterialTheme.colors.onSurface
//                ),
//                singleLine = true, keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Text,
//                    imeAction = ImeAction.Next,
//
//                    ),
//                keyboardActions = KeyboardActions(
//                    onNext = {
//                        focusManager.moveFocus(FocusDirection.Next)
//                    }
//
//                )
//            )
//
//
//        }
//
//        Image(imageVector = Icons.Default.Timer, contentDescription = "", modifier = Modifier
//            .size(36.dp)
//            .weight(1F)
//            .clickable {
//                onTimeSelection()
//            })
//
//
//    }

}



