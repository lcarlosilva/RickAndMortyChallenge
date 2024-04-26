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
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import br.com.luiz.domain.model.Character

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharactersListColumn(
	modifier: Modifier = Modifier,
	items: LazyPagingItems<List<Character>>,
	listState: LazyListState = rememberLazyListState(),
	navigate: (Int) -> Unit = {}
) {
	LazyColumn(
		state = listState,
		modifier = modifier
	) {
		items(items.itemCount) { index ->
			items[index]?.forEach { character ->
				CharacterUI(
					character = character,
					modifier = Modifier.animateItemPlacement()
				) { id ->
					navigate(id)
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
								.padding(
									top = 50.dp,
									bottom = 50.dp
								),
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
									if (errorMessage.error.localizedMessage!!
											.contains("404")
									) "Character not Found"
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
									Text(text = "Try Again")
								}
							}
						}
					}
				}
			}
		}
	}
}