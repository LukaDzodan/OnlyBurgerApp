package com.example.onlyburgerapp.presentation.orders_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlyburgerapp.presentation.util.UiEvent
import com.example.onlyburgerapp.ui.theme.OnlyBurgerOrange

//@Preview(showBackground = true)
@Composable
fun OrdersListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: OrdersListViewModel = hiltViewModel(),
) {

    val state = viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.getOrders()
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.ShowSnackbar -> {
                    val result = snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action,
                        duration = SnackbarDuration.Short
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(OrdersListEvent.OnUndoDeleteClick)
                    }
                }

                else -> Unit
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(OrdersListEvent.OnAddOrderClick)
                },
                containerColor = OnlyBurgerOrange,
                modifier = Modifier.size(70.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }

        }

    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(
                    top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding(),
                    bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
                )
        )
        {
            Column {

                Text(
                    text = "Order list",
                    style = MaterialTheme.typography.headlineMedium,
                    color = OnlyBurgerOrange,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                if (state.value.isLoading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            color = OnlyBurgerOrange,
                        )
                    }
                } else if (state.value.orders.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No orders available",
                            color = OnlyBurgerOrange,
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(0.dp, 16.dp, 16.dp, 0.dp)
                    ) {
                        items(state.value.orders) { order ->

                            Box(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                OrderListItem(
                                    order,
                                    onEvent = { viewModel.onEvent(it) },
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}
