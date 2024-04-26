package br.com.luiz.rickandmortychallenge.ui.view.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.luiz.rickandmortychallenge.navigation.Routes.FILTER_SCREEN
import br.com.luiz.rickandmortychallenge.ui.components.CharactersListColumn
import br.com.luiz.rickandmortychallenge.ui.viewmodel.CharactersListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterListScreen(navController: NavHostController) {
	val viewModel = koinViewModel<CharactersListViewModel>()
	val listCharacters = viewModel.listCharacterState.collectAsLazyPagingItems()

	Box(
		modifier = Modifier.fillMaxSize(),
		contentAlignment = Alignment.BottomEnd,
	) {
		CharactersListColumn(
			items = listCharacters, navController = navController
		)
		FloatingActionButton(
			modifier = Modifier.padding(20.dp),
			onClick = {
				navController.navigate(FILTER_SCREEN)
			},
		) {
			Icon(Icons.Filled.Search, contentDescription = "Search")
		}
	}
}
