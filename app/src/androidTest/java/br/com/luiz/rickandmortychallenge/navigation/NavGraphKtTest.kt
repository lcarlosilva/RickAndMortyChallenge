package br.com.luiz.rickandmortychallenge.navigation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class NavGraphTest {

	@get:Rule
	val composeTestRule = createComposeRule()

	private lateinit var navController: TestNavHostController

	@Before
	fun setup() {
		navController = TestNavHostController(ApplicationProvider.getApplicationContext())
		composeTestRule.setContent {
			NavGraph()
		}
	}

	@Test
	fun `NavGraph_starts_at_list_screen`() {
		assertEquals(Routes.LIST_SCREEN, navController.currentDestination?.route)
	}

	@Test
	fun `NavGraph_navigates_to_details_screen`() {
		navController.navigate(Routes.DETAILS_SCREEN)
		assertEquals(Routes.DETAILS_SCREEN, navController.currentDestination?.route)
	}

	@Test
	fun `NavGraph_navigates_to_filter_screen`() {
		navController.navigate(Routes.FILTER_SCREEN)
		assertEquals(Routes.FILTER_SCREEN, navController.currentDestination?.route)
	}
}