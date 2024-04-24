package br.com.luiz.rickandmortychallenge.ui.view.compose

import androidx.compose.foundation.Image
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
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import br.com.luiz.domain.model.Character
import coil.compose.rememberAsyncImagePainter

@Composable
fun characterListScreen(characters: LazyPagingItems<List<Character>>) {
    LazyColumn {
        items(characters.itemCount) { index ->
            characters[index]?.forEach {
                characterItem(character = it)
            }
        }
    }
}

@Composable
fun characterItem(character: Character) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier =
            Modifier
                .padding(8.dp)
                .fillMaxWidth(),
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
