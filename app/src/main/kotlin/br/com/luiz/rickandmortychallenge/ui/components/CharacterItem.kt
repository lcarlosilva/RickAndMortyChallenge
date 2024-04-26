package br.com.luiz.rickandmortychallenge.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.luiz.domain.model.Character

@Composable
fun CharacterItem(
	character: Character,
	modifier: Modifier = Modifier,
	onClick: (NavHostController) -> Unit
) {
	AnimatedVisibility(
		visible = true,
		enter = fadeIn(),
		exit = fadeOut()
	) {
		val context = LocalContext.current
		Card(
			modifier = modifier
				.animateContentSize()
				.padding(6.dp)
				.fillMaxWidth()
				.clickable {
					onClick.invoke(NavHostController(context))
				},
			shape = RoundedCornerShape(6.dp),
		) {
			Row {
				ImageCharacter(
					imageLink = character.image,
					modifier = Modifier
						.fillMaxWidth(0.35f)
						.clip(CutCornerShape(4.dp))
				)
				Spacer(modifier = Modifier.size(6.dp))
				InfoCharacter(
					character = character,
					modifier = Modifier
						.fillMaxSize()
						.padding(4.dp)
				)
			}
		}
	}
}