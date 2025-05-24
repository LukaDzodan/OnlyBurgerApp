package com.example.onlyburgerapp.presentation.order_detail_screen.popUpScreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.onlyburgerapp.domain.model.BurgerSize
import com.example.onlyburgerapp.ui.theme.OnlyBurgerOrange

@Composable
fun PopUpScreen(
    itemType: String,
    onDismiss: () -> Unit,
    onSelect: (BurgerSize) -> Unit,
) {

    val listOfBurgers = listOf<String>(
        "ClassicBurger",
        "JalapenoBurger",
        "OriginalBurger",
        "BaconBurger",
        "CaramelBurger",
        "ChessyBurger",
        "TruffleMayoBurger"
    )

    Popup(
        onDismissRequest = onDismiss,
        alignment = Alignment.Center,
    ) {
        Card(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .border(BorderStroke(2.dp,Color.Black), shape = RoundedCornerShape(30.dp)),
            shape = RoundedCornerShape(30.dp),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(250.dp).background(OnlyBurgerOrange),
            ) {
                when (itemType) {
                    in listOfBurgers -> {
                        PopUpBurgerItem(optionValue = {value ->
                            //Log.d("Popup", "optionValue: $value")
                            onSelect(value)
                        })
                    }

                    "Drink" -> {
                        Text(text = "ffawf")
                    }

                    "Sauce" -> {
                        Text(text = "ffawf")
                    }
                }
            }
        }
    }
}