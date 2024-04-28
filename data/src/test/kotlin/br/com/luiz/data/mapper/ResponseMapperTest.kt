package br.com.luiz.data.mapper

import br.com.luiz.data.model.ApiResponse
import br.com.luiz.data.model.LocationResponse
import br.com.luiz.data.model.OriginResponse
import br.com.luiz.domain.model.Location
import br.com.luiz.domain.model.Origin
import br.com.luiz.domain.model.Response
import org.junit.Assert.assertEquals
import org.junit.Test

class ResponseMapperTest {

	@Test
	fun `toEntity converts ApiResponse to Response`() {
		val apiResponse = ApiResponse(
			ApiResponse.Info(1, 2, "next", "prev"),
			listOf("Test")
		)
		val expectedResponse = Response(
			count = 1,
			pages = 2,
			next = "next",
			prev = "prev",
			results = listOf("Test")
		)

		val actualResponse = apiResponse.toEntity { it }

		assertEquals(expectedResponse, actualResponse)
	}

	@Test
	fun `toEntity converts LocationResponse to Location`() {
		val locationResponse = LocationResponse("Test")
		val expectedLocation = Location("Test")

		val actualLocation = locationResponse.toEntity()

		assertEquals(expectedLocation, actualLocation)
	}

	@Test
	fun `toEntity converts OriginResponse to Origin`() {
		val originResponse = OriginResponse("Test")
		val expectedOrigin = Origin("Test")

		val actualOrigin = originResponse.toEntity()

		assertEquals(expectedOrigin, actualOrigin)
	}
}