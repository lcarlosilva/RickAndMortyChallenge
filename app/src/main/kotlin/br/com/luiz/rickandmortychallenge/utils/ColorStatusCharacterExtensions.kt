package br.com.luiz.rickandmortychallenge.utils

import androidx.compose.ui.graphics.Color
import br.com.luiz.domain.model.Character

fun colorStatus(item: Character) =
    when (item.status) {
        "Alive" -> Color.Green
        "Dead" -> Color.Red
        else -> Color.Gray
    }
