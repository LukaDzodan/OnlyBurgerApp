package com.example.onlyburgerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.onlyburgerapp.presentation.confirm_order_screen.ConfirmOrderListScreen
import com.example.onlyburgerapp.presentation.order_detail_screen.OrderDetailScreen
import com.example.onlyburgerapp.presentation.orders_list_screen.OrdersListScreen
import com.example.onlyburgerapp.presentation.util.Routes.confirmOrderScreen
import com.example.onlyburgerapp.presentation.util.Routes.orderDetailScreen
import com.example.onlyburgerapp.presentation.util.Routes.ordersListScreen
import com.example.onlyburgerapp.presentation.util.UiEvent
import com.example.onlyburgerapp.ui.theme.OnlyBurgerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnlyBurgerAppTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = ordersListScreen) {

                    composable(ordersListScreen) {
                        OrdersListScreen(
                            onNavigate = { event ->
                                navController.navigate(event.route)
                            }
                        )
                    }

                    composable(
                        route = "$orderDetailScreen?orderId={orderId}",
                        arguments = listOf(
                            navArgument(name = "orderId") {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        OrderDetailScreen(
                            onNavigate = { event ->
                                navController.navigate(event.route)
                            }
                        )
                    }
                    composable(
                        route = "$confirmOrderScreen?order={order}",
                        arguments = listOf(
                            navArgument(name = "order"){
                                type = NavType.StringType
                                nullable = true
                                defaultValue = null
                            }
                        )
                    ) {
                        ConfirmOrderListScreen(
                            //Ne treba ti popbackStack, to je Navigate obican
                            onNavigate = { event ->
                                navController.navigate(event.route)
                            }
                        )
                    }
                }

            }
        }
    }
}

