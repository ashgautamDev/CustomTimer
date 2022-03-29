package com.ashish.custometimer.ui.component

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashish.custometimer.ui.main.MainViewModel

@Composable
fun TopAppBar(viewModel: MainViewModel) {
    val context = LocalContext.current
    val expanded = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Hello Folk! ",
            fontSize = 32.sp,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.SemiBold
        )
        Image( colorFilter = ColorFilter.tint(color = MaterialTheme.colors.surface),
            modifier = Modifier.clickable {
                                          expanded.value = true
            },
            imageVector = Icons.Default.MoreVert,
            contentDescription = null
        )
        DropdownMenu(
            expanded = expanded.value,
            offset = DpOffset((-40).dp, (-40).dp),
            onDismissRequest = { expanded.value = false }) {

                DropdownMenuItem(onClick = {
//                    viewModel.deleteAllCustomeTask()
//                    Toast.makeText(context, "You have empty Tasks List", Toast.LENGTH_SHORT).show()
                    expanded.value = false
                }) {
                    Text(text = "Delete All ")
                }
            DropdownMenuItem(onClick = {
                    expanded.value = false
                }) {
                    Text(text = "Share ")
                }
            DropdownMenuItem (onClick = {
                    expanded.value = false
                }) {
                    Text(text = "About")
                }
            }
        }

    }




