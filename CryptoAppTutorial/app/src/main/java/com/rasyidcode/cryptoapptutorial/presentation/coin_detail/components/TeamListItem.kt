package com.rasyidcode.cryptoapptutorial.presentation.coin_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import com.rasyidcode.cryptoapptutorial.data.remote.dto.coin_detail.Team

@Composable
fun TeamListItem(
    teamMember: Team,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        Text(
            text = teamMember.position,
            style = MaterialTheme.typography.h6
        )
        Text(
            text = teamMember.name,
            style = MaterialTheme.typography.body2,
            fontStyle = FontStyle.Italic
        )
    }
}