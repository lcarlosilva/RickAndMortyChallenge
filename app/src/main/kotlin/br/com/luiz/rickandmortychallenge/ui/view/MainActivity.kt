package br.com.luiz.rickandmortychallenge.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.luiz.rickandmortychallenge.ui.theme.RickAndMortyChallengeTheme
import br.com.luiz.rickandmortychallenge.ui.view.compose.characterListScreen
import br.com.luiz.rickandmortychallenge.ui.viewmodel.CharactersListViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = koinViewModel<CharactersListViewModel>()
            RickAndMortyChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val listCharacters = viewModel.listCharacterState.collectAsLazyPagingItems()
                    characterListScreen(characters = listCharacters)
                }
            }
        }
    }
}
