package com.example.onlyburgerapp.presentation.order_detail_screen.lazyVerticalGridDataListScreen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.onlyburgerapp.domain.model.Sauce
import com.example.onlyburgerapp.ui.theme.OnlyBurgerOrange

@Composable
fun LazyVerticalGridSauceList(
    sauces : List<Sauce>,
    sauceToAdd : (Sauce) -> Unit
) {
    // Ne valja smisli novu logiku
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize()
        ) {
            items(sauces) { sauce ->
                Card(
                    shape = CardDefaults.shape,
                    modifier = Modifier
                        .background(Color.Black)
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(8.dp)
                        .clickable{
                            sauceToAdd(sauce)
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(OnlyBurgerOrange),
                        contentAlignment = Alignment.Center // Centriranje teksta unutar Box-a
                    ) {
                        LazyVerticalGridItem(
                            text = when (sauce) {
                                is Sauce.BBQSauce -> "BBQ Sauce"
                                is Sauce.CreamSauce -> "Cream Sauce"
                                is Sauce.HomeMadeBurgerSauce -> "Home made burger Sauce"
                                is Sauce.HoneyMustardSauce -> "Honey Mustard Sauce"
                                is Sauce.KetchupSauce -> "Ketchup Sauce"
                                is Sauce.MayonnaiseSauce -> "Mayonnaise Sauce"
                                is Sauce.SweetChillySauce -> "Sweet Chilly Sauce"
                                is Sauce.TruffleMayoSauce -> "Truffle Mayo Sauce"
                            }
                        )
                    }
                }
            }
        }
    }
}
