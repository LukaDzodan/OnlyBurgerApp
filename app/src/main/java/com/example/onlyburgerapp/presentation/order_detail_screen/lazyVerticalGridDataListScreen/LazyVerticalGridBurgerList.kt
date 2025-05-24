package com.example.onlyburgerapp.presentation.order_detail_screen.lazyVerticalGridDataListScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.onlyburgerapp.domain.model.Burger
import com.example.onlyburgerapp.domain.model.BurgerSize
import com.example.onlyburgerapp.presentation.order_detail_screen.popUpScreen.PopUpScreen
import com.example.onlyburgerapp.ui.theme.OnlyBurgerOrange

@Composable
fun LazyVerticalGridBurgerList(
    burgers: List<Burger>,
    burgerToAdd : (Burger) -> Unit
) {

    val selectedBurgerSize = remember { mutableStateOf<BurgerSize?>(null) }
    val showPopup = remember { mutableStateOf(false) }
    val selectedBurgerName = remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (showPopup.value) {

            PopUpScreen(
                itemType = selectedBurgerName.value,
                onDismiss = {showPopup.value = false},
                onSelect = { selectedSize ->
                    selectedBurgerSize.value = selectedSize
                    showPopup.value = false

                    val newBurger = when (selectedBurgerName.value) {
                        "ClassicBurger" -> Burger.ClassicBurger(selectedBurgerSize.value!!) // ili uzmi veličinu iz popupa kasnije
                        "OriginalBurger" -> Burger.OriginalBurger(selectedBurgerSize.value!!)
                        "TruffleMayoBurger" -> Burger.TruffleMayoBurger(selectedBurgerSize.value!!)
                        "BaconBurger" -> Burger.BaconBurger(selectedBurgerSize.value!!)
                        "JalapenoBurger" -> Burger.JalapenoBurger(selectedBurgerSize.value!!)
                        "ChessyBurger" -> Burger.ChessBurger(selectedBurgerSize.value!!)
                        "CaramelBurger" -> Burger.CaramelBurger(selectedBurgerSize.value!!)
                        else -> null}
                    burgerToAdd(newBurger!!)
                }
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize()
        ) {
            items(burgers) { burger ->
                Card(
                    shape = CardDefaults.shape,
                    modifier = Modifier
                        .background(Color.Black)
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(8.dp)
                        .clickable {
                            if(burger.burgerName == "OnlyBurger"){
                                burgerToAdd(Burger.OnlyBurger)
                            }
                            else if(burger.burgerName == "FatBoyBurger"){
                                burgerToAdd(Burger.FatBoyBurger)
                            }
                            else{
                                selectedBurgerName.value = burger.burgerName
                                showPopup.value = true
                            }
                        }) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(OnlyBurgerOrange),
                        contentAlignment = Alignment.Center
                    ) {
                        LazyVerticalGridItem(
                            text = when (burger) {
                                is Burger.BaconBurger -> "Bacon Burger"
                                is Burger.CaramelBurger -> "Caramel Burger"
                                is Burger.ChessBurger -> "Chessy Burger"
                                is Burger.ClassicBurger -> "Classic Burger"
                                is Burger.FatBoyBurger -> "Fat Boy Burger"
                                is Burger.JalapenoBurger -> "Jalapeno Burger"
                                is Burger.OnlyBurger -> "Only Burger"
                                is Burger.OriginalBurger -> "Original Burger"
                                is Burger.TruffleMayoBurger -> "Truffle Mayo Burger"
                            }
                        )
                    }
                }
            }
            }
        }
    }
