package br.com.luiz.rickandmortychallenge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.luiz.domain.model.Character
import br.com.luiz.rickandmortychallenge.navigation.Routes.DETAILS_SCREEN
import br.com.luiz.rickandmortychallenge.navigation.Routes.LIST_SCREEN
import br.com.luiz.rickandmortychallenge.ui.view.compose.CharacterListScreen
import br.com.luiz.rickandmortychallenge.ui.view.compose.detailsCharacterBottomSheet
import kotlinx.serialization.json.Json
import java.net.URLDecoder

@Suppress("ktlint:standard:function-naming")
@Composable
fun NavGraph(startDestination: String = LIST_SCREEN) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(
            route = LIST_SCREEN,
        ) {
            CharacterListScreen(navController = navController)
        }

        composable(
            route = "$DETAILS_SCREEN/{item}",
            arguments = listOf(navArgument("item") { type = NavType.StringType }),
        ) { backStackEntry ->
            val item = URLDecoder.decode(backStackEntry.arguments?.getString("item"), "utf-8")
            val character = Json.decodeFromString<Character>(item!!)
            detailsCharacterBottomSheet(item = character) {
                navController.popBackStack()
            }
        }
    }
}
