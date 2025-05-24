package com.example.onlyburgerapp.presentation.order_detail_screen


import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onlyburgerapp.R
import com.example.onlyburgerapp.R.drawable.selected_burger_icon
import com.example.onlyburgerapp.R.drawable.selected_sauces_icon
import com.example.onlyburgerapp.R.drawable.unselected_burger_icon
import com.example.onlyburgerapp.R.drawable.unselected_sauces_icon
import com.example.onlyburgerapp.data.OrderList
import com.example.onlyburgerapp.domain.model.Burger
import com.example.onlyburgerapp.presentation.order_detail_screen.lazyVerticalGridDataListScreen.LazyVerticalGridBurgerList
import com.example.onlyburgerapp.presentation.order_detail_screen.lazyVerticalGridDataListScreen.LazyVerticalGridDrinkList
import com.example.onlyburgerapp.presentation.order_detail_screen.lazyVerticalGridDataListScreen.LazyVerticalGridSauceList
import com.example.onlyburgerapp.presentation.util.MenuData
import com.example.onlyburgerapp.presentation.util.UiEvent
import com.example.onlyburgerapp.ui.theme.OnlyBurgerOrange
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.enums.EnumEntries

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailScreen(
    viewModel: OrderDetailViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { OrderTabs.entries.size })
    val selectedTabIndex = remember {
        derivedStateOf { pagerState.currentPage }
    }


    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.ShowSnackbar -> {
                    launch {
                        snackbarHostState.showSnackbar(
                            message = event.message,
                            actionLabel = event.action,
                            duration = SnackbarDuration.Indefinite
                        )
                    }
                    launch {
                        delay(1500L)
                        snackbarHostState.currentSnackbarData?.dismiss()
                    }
                }

                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add order",
                        textAlign = TextAlign.Center,
                        color = OnlyBurgerOrange
                    )
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
//                    viewModel.orderList = viewModel.orderList?.copy(
//                        burger = orderList.value.burger?.plus(orderBurgerList.value)
//                    )
                    viewModel.onEvent(OrderDetailEvent.OnSaveOrderClick)

                    //Treba da napravis event da kada se doda bilo sta na order, da se promeni orderList u viewModelu
                },
                containerColor = OnlyBurgerOrange,
                modifier = Modifier.size(70.dp)
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "")
            }
        },
        containerColor = Color.Black
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
                )
                .background(Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
                    .background(Color.Black)
            ) {
                TabRow(
                    selectedTabIndex = selectedTabIndex.value,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OrderTabs.entries.forEachIndexed { index, currentTab ->
                        Tab(
                            modifier = Modifier
                                .background(OnlyBurgerOrange)
                                .height(80.dp),
                            selected = selectedTabIndex.value == index,
                            selectedContentColor = MaterialTheme.colorScheme.primary,
                            unselectedContentColor = MaterialTheme.colorScheme.outline,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(currentTab.ordinal)
                                }
                            },
                            text = { Text(text = currentTab.text, color = Color.Black) },
                            icon = {
                                Image(
                                    painter = painterResource(
                                        id = if (selectedTabIndex.value == index)
                                            currentTab.selectedIcon
                                        else
                                            currentTab.unselectedIcon
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(32.dp)
                                )
                            }
                        )
                    }
                }
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                ) { page ->
                    when (OrderTabs.entries[page]) {
                        OrderTabs.Burgers -> LazyVerticalGridBurgerList(
                            MenuData.burgers,
                            burgerToAdd = { burger ->
//                                orderBurgerList.value = burgers
//                                orderList.copy(
//                                    burger = orderBurgerList.value
//                                )
                                viewModel.onEvent(OrderDetailEvent.OnAddBurgerClick(burger))
                            })

                        OrderTabs.Drinks -> LazyVerticalGridDrinkList(
                            MenuData.drinks,
                            drinkToAdd = { drink ->
                                viewModel.onEvent(OrderDetailEvent.OnAddDrinkClick(drink))
                            }
                        )

                        OrderTabs.Sauces -> LazyVerticalGridSauceList(
                            MenuData.sauces,
                            sauceToAdd = {sauce ->
                                viewModel.onEvent(OrderDetailEvent.OnAddSauceClick(sauce))
                            }
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = viewModel.adress,
                        onValueChange = { viewModel.onEvent(OrderDetailEvent.OnAdressChange(it)) },
                        label = { Text("Unesite adresu") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = viewModel.description,
                        onValueChange = { viewModel.onEvent(OrderDetailEvent.OnDescriptionChange(it)) },
                        label = { Text("Dodatni opis porudžbine") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(top = 8.dp),
                        singleLine = false
                    )
                }
            }

        }
    }
}


enum class OrderTabs(
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val text: String,
) {

    Burgers(
        selectedIcon = selected_burger_icon,
        unselectedIcon = unselected_burger_icon,
        text = "Burgers"
    ),

    Drinks(
        selectedIcon = R.drawable.selected_drink_icon,
        unselectedIcon = R.drawable.unselected_drink_icon,
        text = "Drinks"
    ),

    Sauces(
        selectedIcon = selected_sauces_icon,
        unselectedIcon = unselected_sauces_icon,
        text = "Sauces"
    )
}

