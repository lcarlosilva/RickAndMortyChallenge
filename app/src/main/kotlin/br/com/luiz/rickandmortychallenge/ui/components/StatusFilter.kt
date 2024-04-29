package br.com.luiz.rickandmortychallenge.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.UUID


@Composable
fun Filter(list: List<CharacterStatusData>, onStatusSelected: (CharacterStatusData) -> Unit) {
	val statusState = remember { CharacterStatusState() }
	statusState.setItemList(list)

	LazyRow(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 30.dp, vertical = 18.dp),
		horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
		contentPadding = PaddingValues(horizontal = 8.dp)
	) {
		items(statusState.itemList, key = { it.id }) { characterStatusData ->
			Item(
				item = characterStatusData,
				onItemSelected = {
					val itemSelected =
						statusState.onItemSelected(it)
					onStatusSelected(itemSelected)
				}
			)
		}
	}
}

@Composable
fun Item(
	item: CharacterStatusData,
	onItemSelected: (CharacterStatusData) -> Unit
) {
	Card(
		modifier = Modifier.size(width = 100.dp, height = 40.dp),
		border = if (item.isSelected) BorderStroke(2.dp, Color.Cyan) else null,
		/*colors = CardColors(
			containerColor = Color.Gray,
			contentColor = Color.White,
			disabledContainerColor = Color.LightGray,
			disabledContentColor = Color.LightGray,
		)*/
	) {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.clickable {
					onItemSelected(item)
				},
			contentAlignment = Alignment.Center
		) {
			Text(text = item.status.name.lowercase())
		}
	}

}

data class CharacterStatusData(
	val id: UUID = UUID.randomUUID(),
	val status: StatusFilter,
	val isSelected: Boolean = false
) {
	enum class StatusFilter {
		ALIVE,
		DEAD,
		UNKNOWN
	}
}

class CharacterStatusState {

	var itemList = mutableStateListOf<CharacterStatusData>()

	fun onItemSelected(characterStatus: CharacterStatusData): CharacterStatusData {
		val iterator = itemList.listIterator()
		var selectedItem: CharacterStatusData? = null
		while (iterator.hasNext()) {
			val item = iterator.next()
			iterator.set(
				if (item.status == characterStatus.status) {
					selectedItem = characterStatus
					characterStatus
				} else {
					item.copy(isSelected = item.isSelected.not())
				}
			)
		}
		return selectedItem ?: characterStatus
	}

	fun setItemList(list: List<CharacterStatusData>) {
		this.itemList = list.toMutableStateList()
	}
}

