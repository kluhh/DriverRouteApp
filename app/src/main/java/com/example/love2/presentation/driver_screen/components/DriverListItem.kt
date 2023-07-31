package com.example.love2.presentation.driver_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.love2.domain.model.DriverItem

@Composable
fun DriverListItem(
    driverItem: DriverItem,
    onItemClick: (DriverItem) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(driverItem) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "Driver Id: ${driverItem.id} Name: ${driverItem.name}",
            style = MaterialTheme.typography.labelLarge,
            overflow = TextOverflow.Ellipsis
        )

    }
}
