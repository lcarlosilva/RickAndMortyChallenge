package br.com.luiz.rickandmortychallenge.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.com.luiz.commons.utils.extensions.emptyString
import br.com.luiz.rickandmortychallenge.R

@Composable
fun SearchBar(
	value: String,
	placeholder: String,
	navigateUp: () -> Unit,
	onValueChange: (String) -> Unit,
	onValueSelected: (CharacterStatusData) -> Unit
) {
	val focusManager = LocalFocusManager.current
	val requester = remember { FocusRequester() }

	Column {
		Row(
			modifier = Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically
		) {
			IconButton(onClick = { navigateUp() }) {
				Icon(
					imageVector = Icons.AutoMirrored.Filled.ArrowBack,
					contentDescription = stringResource(R.string.lbl_content_description_back_screen)
				)
			}
			TextField(
				value = value,
				onValueChange = { name -> onValueChange(name) },
				placeholder = { Text(text = placeholder) },
				colors = TextFieldDefaults.colors(
					focusedIndicatorColor = Color.Transparent,
					unfocusedIndicatorColor = Color.Transparent,
				),
				modifier = Modifier
					.fillMaxWidth()
					.focusRequester(
						focusRequester = requester
					),
				trailingIcon = {
					if (value.isNotBlank()) {
						IconButton(onClick = {
							onValueChange(emptyString())
						}) {
							Icon(
								imageVector = Icons.Default.Clear,
								contentDescription = stringResource(R.string.lbl_content_description_clear_search),
								modifier = Modifier
									.padding(end = 8.dp)
									.size(20.dp)
							)
						}
					}
				},
				keyboardOptions = KeyboardOptions(
					keyboardType = KeyboardType.Text,
					imeAction = ImeAction.Done
				),
				keyboardActions = KeyboardActions(
					onDone = { focusManager.clearFocus() }
				)
			)
		}
		HorizontalDivider(
			modifier = Modifier.fillMaxWidth(),
			thickness = 1.dp,
			color = Color.DarkGray
		)
		Filter(
			listOf(
				CharacterStatusData(status = CharacterStatusData.StatusFilter.ALIVE),
				CharacterStatusData(status = CharacterStatusData.StatusFilter.DEAD),
				CharacterStatusData(status = CharacterStatusData.StatusFilter.UNKNOWN)
			),
			onStatusSelected = { characterStatusData -> onValueSelected(characterStatusData) }
		)
		HorizontalDivider(
			modifier = Modifier.fillMaxWidth(),
			thickness = 1.dp,
			color = Color.DarkGray
		)
	}
	SideEffect { requester.requestFocus() }
}