package ca.qc.chc.andriond.listdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ca.qc.chc.andriond.listdemo.ui.theme.NavigationExampleTheme

class ListDemo : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    innerPadding -> MyNavigation(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }

@Composable
fun MyNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "HomePage",
        modifier = modifier
    ) {

        composable("HomePage") {
            HomePage(navController = navController)
        }

        composable("ColumnPage") {
            ColumnPage(navController = navController)
        }

        composable("RowPage") {
            RowPage(navController = navController)
        }

        composable("GridPage") {
            GridPage(navController = navController)
        }

        composable(
            route = "DetailsPage/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val countryId = backStackEntry.arguments?.getInt("id")

            // “Only execute let { } if countryId is NOT null.”
            countryId?.let { id ->
                DetailsPage(navController = navController, id = id)
            }
        }
    }
}

@Preview(showBackground = true,
    showSystemUi = true,
    device = "spec:width=1080dp,height=2400dp,dpi=440")

@Composable
fun MyNavigationPreview() {
    NavigationExampleTheme {
        MyNavigation()
    }
}