package com.ashish.custometimer.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AppFab(icon: ImageVector = Icons.Default.AddCircle, onClick: () -> Unit) {
    Image(
        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary),
        imageVector = icon.apply { MaterialTheme.colors.onPrimary },
        contentDescription = null,
        modifier = Modifier
            .size(60.dp)
            .clickable {
                onClick()
            })
}