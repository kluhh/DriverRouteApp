package com.example.love2.presentation.driver_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
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
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Use a Column to stack the driver's ID and name vertically
        Column {
            Text(
                text = "Driver ID: ${driverItem.id}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Name: ${driverItem.name}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        // Display an icon next to the driver's information
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}
