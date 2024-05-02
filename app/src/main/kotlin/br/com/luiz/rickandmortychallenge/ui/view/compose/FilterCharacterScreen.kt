package br.com.luiz.rickandmortychallenge.ui.view.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.luiz.commons.utils.extensions.emptyString
import br.com.luiz.rickandmortychallenge.R
import br.com.luiz.rickandmortychallenge.ui.components.CharactersListColumn
import br.com.luiz.rickandmortychallenge.ui.components.SearchBar
import br.com.luiz.rickandmortychallenge.ui.viewmodel.FilterCharacterViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun filterCharacterScreen(navController: NavHostController, navigateBack: () -> Unit) {

	val viewModel = koinViewModel<FilterCharacterViewModel>()
	val state = viewModel.searchResult.value
	val characters = state.dataList?.collectAsLazyPagingItems()
	val searchString = viewModel.searchString.collectAsState().value

	LaunchedEffect(key1 = searchString) {
		if (searchString != emptyString()) {
			viewModel.searchCharacterByName(searchString)
		}
	}

	Scaffold(
		topBar = {
			SearchBar(
				value = searchString,
				placeholder = stringResource(R.string.txt_placeholder_search_characters),
				navigateUp = navigateBack,
				onValueChange = { name ->
					viewModel.searchCharacter(name)
				},
				onValueSelected = { characterStatus ->
					viewModel.searchCharacterByName(status = characterStatus)
				}
			)
		}
	) { paddingValues ->
		characters?.let { searchCharacters ->
			CharactersListColumn(
				modifier = Modifier.padding(paddingValues),
				items = searchCharacters,
				navController = navController
			)
		}
	}
}
