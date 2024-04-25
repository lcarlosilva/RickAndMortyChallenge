@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.luiz.rickandmortychallenge.ui.view.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.luiz.domain.model.Character
import br.com.luiz.domain.model.Location
import br.com.luiz.domain.model.Origin
import br.com.luiz.rickandmortychallenge.R
import br.com.luiz.rickandmortychallenge.utils.colorStatus
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun detailsCharacterBottomSheet(
    item: Character,
    onDismissRequest: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        modifier =
            Modifier
                .fillMaxHeight(),
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
    ) {
        Box {
            Column(
                modifier =
                    Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                headerImage(item)
                Spacer(modifier = Modifier.height(24.dp))
                contentCharacterInfos(item)
                contentCharacterLocations(item)
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 20.dp),
                    thickness = 1.dp,
                    color = Color.Gray,
                )
                footer(scope, sheetState, onDismissRequest)
            }
        }
    }
}

@Composable
private fun headerImage(item: Character) {
    Image(
        painter = rememberAsyncImagePainter(item.image),
        contentDescription =
            stringResource(
                R.string.lbl_content_description_photo_character,
                item.name,
            ),
        alignment = Alignment.TopCenter,
        modifier =
            Modifier
                .height(200.dp)
                .fillMaxWidth()
                .clip(CutCornerShape(20.dp)),
    )
}

@Composable
private fun contentCharacterInfos(item: Character) {
    Text(
        text = item.name,
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Center,
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val color = colorStatus(item)
        Box(
            modifier =
                Modifier
                    .size(16.dp)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(color),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = item.status, style = MaterialTheme.typography.labelSmall)
    }
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = item.species,
                style =
                    MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                    ),
            )
            Text(
                text = stringResource(R.string.lbl_species),
                style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray),
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = item.gender,
                style =
                    MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                    ),
            )
            Text(
                text = stringResource(R.string.lbl_gender),
                style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray),
            )
        }
    }
}

@Composable
private fun contentCharacterLocations(item: Character) {
    Column(
        modifier =
            Modifier
                .padding(start = 20.dp)
                .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = item.origin.name,
            style =
                MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
        )
        Text(
            text = stringResource(R.string.lbl_origin),
            style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray),
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = item.location.name,
            style =
                MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
        )
        Text(
            text = stringResource(R.string.lbl_location),
            style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray),
        )
    }
}

@Composable
private fun footer(
    scope: CoroutineScope,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .padding(top = 10.dp, end = 10.dp)
                .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
    ) {
        Button(onClick = {
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) {
                    onDismissRequest()
                }
            }
        }) {
            Text(stringResource(R.string.lbl_btn_ok))
        }
    }
}

@Composable
@Preview()
fun detailsCharacterBottomSheetPreview() {
    detailsCharacterBottomSheet(
        item =
            Character(
                id = 1,
                name = "Rick",
                status = "Alive",
                species = "Human",
                type = "",
                gender = "Male",
                origin = Origin(name = "Earth"),
                location = Location(name = "Citadel of Ricks"),
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            ),
    ) {}
}
