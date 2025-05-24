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
import com.example.onlyburgerapp.domain.model.Drink
import com.example.onlyburgerapp.presentation.order_detail_screen.popUpScreen.PopUpScreen
import com.example.onlyburgerapp.ui.theme.OnlyBurgerOrange


@Composable
fun LazyVerticalGridDrinkList(
    drinks : List<Drink>,
    drinkToAdd : (Drink) -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
        ) {
            items(drinks) { drink ->
                //print(drink.toString())
                Card(
                    shape = CardDefaults.shape,
                    modifier = Modifier
                        .background(Color.Black)
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(8.dp)
                        .clickable{
                            drinkToAdd(drink)
//                            Log.d("drinkalo","$drink")
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(OnlyBurgerOrange),
                        contentAlignment = Alignment.Center
                    ) {
                        LazyVerticalGridItem(
                            text = when (drink) {
                                is Drink.CocaCola -> "Coca Cola"
                                is Drink.Cockta -> "Cockta"
                                is Drink.Fanta -> "Fanta"
                                is Drink.Schweppes -> "Schweppes"
                            }
                        )
                    }
                }
            }
        }
    }
}
