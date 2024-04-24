@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.luiz.rickandmortychallenge.ui.view.compose

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import br.com.luiz.domain.model.Character
import kotlinx.coroutines.launch

@Composable
fun detailsCharacterBottomSheet(
    item: Character,
    onDismissRequest: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
    ) {
        Button(onClick = {
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) {
                    onDismissRequest()
                }
            }
        }) {
            Text(item.name)
        }
    }
}
