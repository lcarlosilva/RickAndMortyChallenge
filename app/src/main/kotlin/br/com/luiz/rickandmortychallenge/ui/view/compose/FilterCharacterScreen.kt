package br.com.luiz.rickandmortychallenge.ui.view.compose

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.luiz.rickandmortychallenge.ui.components.CharactersListColumn
import br.com.luiz.rickandmortychallenge.ui.components.CustomSearchBar
import br.com.luiz.rickandmortychallenge.ui.viewmodel.FilterCharacterViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun filterCharacterScreen(
	navigate: (Int) -> Unit,
	navigateBack: () -> Unit
) {

	val viewModel = koinViewModel<FilterCharacterViewModel>()

	val state = viewModel.searchResult.value

	val characters = state.dataList?.collectAsLazyPagingItems()

	val searchString = viewModel.searchString.collectAsState().value

	LaunchedEffect(key1 = searchString) {
		if (searchString != "") {
			viewModel.searchCharacterbyName(searchString)
		}
	}


	Scaffold(
		topBar = {
			CustomSearchBar(
				value = searchString,
				placeholder = "Search Characters",
				navigateUp = navigateBack,
				onValueChange = { name ->
					viewModel.searchCharacter(name)
				}
			)
		}
	) { _ ->
		characters?.let { searchCharacters->
			CharactersListColumn(
				items = searchCharacters,
				/*navigate = navigate*/
			)
		}
	}
}
