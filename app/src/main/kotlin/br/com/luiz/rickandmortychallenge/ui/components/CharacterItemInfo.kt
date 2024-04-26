package br.com.luiz.rickandmortychallenge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.palette.graphics.Palette
import br.com.luiz.domain.model.Character
import br.com.luiz.rickandmortychallenge.R
import br.com.luiz.rickandmortychallenge.utils.colorStatus
import coil.request.ImageRequest
import coil.transform.Transformation
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.palette.BitmapPalette

@Composable
fun ImageCharacter(
	imageLink: String,
	modifier: Modifier,
	transformations: List<Transformation> = emptyList(),
	bitPallette: (Palette) -> Unit = {}
) {
	CoilImage(
		imageRequest =
		ImageRequest
			.Builder(LocalContext.current)
			.data(imageLink)
			.crossfade(true)
			.transformations(transformations)
			.build(),
		alignment = Alignment.Center,
		loading = {
			ConstraintLayout(
				modifier = Modifier
					.fillMaxSize()
					.placeholder(
						visible = true,
						highlight = PlaceholderHighlight.shimmer(),
					)
			) {
				val indicator = createRef()
				CircularProgressIndicator(
					modifier = Modifier.constrainAs(indicator) {
						top.linkTo(parent.top)
						bottom.linkTo(parent.bottom)
						start.linkTo(parent.start)
						end.linkTo(parent.end)
					}
				)
			}
		},
		circularReveal = CircularReveal(
			duration = 300,
		),
		modifier = modifier,
		bitmapPalette = BitmapPalette { pallette ->
			bitPallette(pallette)
		}
	)
}

@Composable
fun InfoCharacter(
	character: Character,
	modifier: Modifier = Modifier,
	alignment: Alignment.Horizontal = Alignment.Start
) {
	val color = colorStatus(character)
	Column(
		modifier = modifier
			.fillMaxHeight()
			.fillMaxWidth(),
		horizontalAlignment = alignment
	) {
		Text(
			text = character.name,
			style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
			maxLines = 1,
			overflow = TextOverflow.Ellipsis
		)
		Spacer(modifier = Modifier.height(6.dp))
		Column(horizontalAlignment = Alignment.Start) {
			Text(
				text = stringResource(R.string.lbl_species),
				style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray),
			)
			Text(
				text = character.species,
				style =
				MaterialTheme.typography.bodyMedium.copy(
					fontWeight = FontWeight.Bold,
				),
			)
		}
		Text(
			text = stringResource(R.string.lbl_status),
			style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray)
		)
		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.Start,
			verticalAlignment = Alignment.CenterVertically,
		) {
			Box(
				modifier =
				Modifier
					.size(16.dp)
					.padding(4.dp)
					.clip(CircleShape)
					.background(color),
			)
			Text(text = character.status, style = MaterialTheme.typography.bodySmall)
		}
	}
}