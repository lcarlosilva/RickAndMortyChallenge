package br.com.luiz.rickandmortychallenge.ui.model

import java.util.UUID

data class CharacterStatusUiData(
	val id: UUID = UUID.randomUUID(),
	val status: StatusFilter
) {
	enum class StatusFilter {
		ALIVE,
		DEAD,
		UNKNOWN
	}
}