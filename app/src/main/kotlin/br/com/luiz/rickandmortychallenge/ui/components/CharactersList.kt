package br.com.luiz.rickandmortychallenge.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import br.com.luiz.domain.model.Character
import br.com.luiz.rickandmortychallenge.R
import br.com.luiz.rickandmortychallenge.navigation.Routes
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharactersListColumn(
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
		items.apply {
			when {
				loadState.refresh is LoadState.Loading -> {
					item {
						Box(
							modifier = Modifier
								.fillMaxSize()
								.padding(vertical = 50.dp),
							contentAlignment = Alignment.Center
						) {
							CircularProgressIndicator(modifier = Modifier.height(30.dp))
						}
					}
				}

				loadState.append is LoadState.Loading -> {
					item {
						Box(
							modifier = Modifier
								.fillMaxWidth()
								.padding(bottom = 56.dp),
							contentAlignment = Alignment.Center
						) {
							CircularProgressIndicator(modifier = Modifier.height(30.dp))
						}
					}
				}

				loadState.refresh is LoadState.Error -> {
					val errorMessage = items.loadState.refresh as LoadState.Error
					item {
						Box(
							modifier = Modifier
								.fillMaxWidth()
								.padding(bottom = 56.dp),
							contentAlignment = Alignment.BottomCenter
						) {
							Column(
								modifier = Modifier.fillMaxWidth(),
								horizontalAlignment = Alignment.CenterHorizontally
							) {
								val errorText =
									if (errorMessage.error.localizedMessage!!.contains("404"))
										stringResource(R.string.msg_error_character_not_found)
									else
										errorMessage.error.localizedMessage
								Text(errorText)
							}
						}
					}
				}

				loadState.append is LoadState.Error -> {
					val errorMessage = items.loadState.append as LoadState.Error
					item {
						Box(
							modifier = Modifier
								.fillMaxWidth()
								.padding(bottom = 56.dp),
							contentAlignment = Alignment.BottomCenter
						) {
							Column(
								modifier = Modifier.fillMaxWidth(),
								horizontalAlignment = Alignment.CenterHorizontally
							) {
								Text(text = errorMessage.error.localizedMessage!!)
								Button(onClick = { retry() }) {
									Text(text = stringResource(R.string.msg_info_try_again))
								}
							}
						}
					}
				}
			}
		}
	}
}