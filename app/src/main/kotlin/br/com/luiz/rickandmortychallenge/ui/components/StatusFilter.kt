package br.com.luiz.rickandmortychallenge.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.luiz.rickandmortychallenge.ui.model.CharacterStatusUiData
import br.com.luiz.rickandmortychallenge.ui.theme.Purple40
import br.com.luiz.rickandmortychallenge.ui.theme.PurpleGrey80


@Composable
fun Filter(list: List<CharacterStatusUiData>, onStatusSelected: (String) -> Unit) {
	var selected by remember { mutableStateOf("") }
	LazyRow(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 30.dp, vertical = 18.dp),
		horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
		contentPadding = PaddingValues(horizontal = 8.dp)
	) {
		items(list, key = { it.id }) { characterStatusData ->
			Item(
				title = characterStatusData.status.name,
				selected = selected,
				onItemSelected = {
					selected = it
					onStatusSelected(it)
				}
			)
		}
	}
}

@Composable
fun Item(
	title: String,
	selected: String,
	onItemSelected: (String) -> Unit
) {
	val isSelected = selected == title
	val backgroundColor = if (isSelected) Purple40 else PurpleGrey80
	val textColor = if (isSelected) Color.White else Color.Black

	Box(modifier = Modifier
		.padding(10.dp)
		.height(35.dp)
		.clip(CircleShape)
		.background(color = backgroundColor)
		.clickable {
			onItemSelected(title)
		}) {
		Row(
			modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.spacedBy(5.dp)
		) {
			AnimatedVisibility(visible = isSelected) {
				Icon(
					imageVector = Icons.Filled.Check,
					contentDescription = "Check",
					tint = Color.White
				)
			}
			Text(text = title.lowercase(), color = textColor)
		}
	}
}

