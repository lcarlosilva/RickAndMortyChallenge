package br.com.luiz.rickandmortychallenge.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import br.com.luiz.domain.model.Character
import br.com.luiz.rickandmortychallenge.navigation.Routes
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharactersList(
	modifier: Modifier = Modifier,
	items: LazyPagingItems<List<Character>>,
	listState: LazyListState = rememberLazyListState(),
	navController: NavHostController
) {
	LazyColumn(
		state = listState,
		modifier = modifier
	) {
		items(items.itemCount) { index ->
			items[index]?.forEach { character ->
				CharacterItem(
					character = character,
					modifier = Modifier.animateItemPlacement()
				) {
					val characterJson = URLEncoder.encode(Json.encodeToString(character), "utf-8")
					navController.navigate("${Routes.DETAILS_SCREEN}/$characterJson")
				}
			}
		}
	}
}