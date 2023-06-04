package ibu.edu.unitask.ui.navigation

import android.icu.text.IDNA.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ibu.edu.unitask.ui.home.HomeDestination
import ibu.edu.unitask.ui.home.HomeScreen
import ibu.edu.unitask.ui.add_task.AddNewTaskDestination
import ibu.edu.unitask.ui.add_task.AddNewTaskScreen
import ibu.edu.unitask.ui.details.TaskDetailsDestination
import ibu.edu.unitask.ui.details.TaskDetailsScreen
import ibu.edu.unitask.ui.finished.FinishedTasksDestination
import ibu.edu.unitask.ui.finished.FinishedTasksScreen
import ibu.edu.unitask.ui.info.InfoDestination
import ibu.edu.unitask.ui.info.InfoScreen

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun UniTaskNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {


        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToAddTask = { navController.navigate(AddNewTaskDestination.route) },
                navigateToDetails = {
                    navController.navigate("task_details/$it")
                }
            )
        }

        composable(route= AddNewTaskDestination.route){
            AddNewTaskScreen(
                onNavigateUp = {navController.navigate(HomeDestination.route)}
            )
        }

        composable(
            route = TaskDetailsDestination.route,
            arguments = listOf(navArgument("id"){
                type = NavType.IntType
            })
        ){
            //because I don't have null safe taskId variable I need to put this .let function,
            // if I were to have that then I would need to change everything from repo level
            it.arguments?.getInt("id")?.let { it1 ->
                TaskDetailsScreen(
                    taskId = it1,
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }

        composable(route = FinishedTasksDestination.route){
            FinishedTasksScreen(
                onRequestDetails = {
                    navController.navigate("task_details/$it")
                }
            )
        }

        composable(route = InfoDestination.route){
            InfoScreen()
        }


    }
}