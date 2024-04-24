package br.com.luiz.rickandmortychallenge.ui.view.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.luiz.domain.model.Character
import br.com.luiz.rickandmortychallenge.navigation.Routes.DETAILS_SCREEN
import br.com.luiz.rickandmortychallenge.ui.viewmodel.CharactersListViewModel
import coil.compose.rememberAsyncImagePainter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel
import java.net.URLEncoder

@Suppress("ktlint:standard:function-naming")
@Composable
fun CharacterListScreen(navController: NavHostController) {
    val viewModel = koinViewModel<CharactersListViewModel>()
    val listCharacters = viewModel.listCharacterState.collectAsLazyPagingItems()
    characterListScreen(characters = listCharacters, navController)
}

@Composable
fun characterListScreen(
    characters: LazyPagingItems<List<Character>>,
    navController: NavHostController,
) {
    LazyColumn {
        items(characters.itemCount) { index ->
            characters[index]?.forEach { character ->
                characterItem(character = character) {
                    val characterJson = URLEncoder.encode(Json.encodeToString(character), "utf-8")
                    navController.navigate("$DETAILS_SCREEN/$characterJson")
                }
            }
        }
    }
}

@Composable
fun characterItem(
    character: Character,
    onCharClicked: (NavHostController) -> Unit,
) {
    val context = LocalContext.current
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier =
            Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable {
                    onCharClicked.invoke(NavHostController(context))
                },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp),
        ) {
            Image(
                painter = rememberAsyncImagePainter(character.image),
                contentDescription = null,
                modifier =
                    Modifier
                        .size(64.dp)
                        .clip(CircleShape),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = character.name, style = MaterialTheme.typography.headlineMedium)
                Text(text = character.species, style = MaterialTheme.typography.bodyMedium)
                Text(text = character.status, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
