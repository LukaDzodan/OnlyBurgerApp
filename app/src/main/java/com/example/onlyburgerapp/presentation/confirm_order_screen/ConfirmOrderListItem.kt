package com.example.onlyburgerapp.presentation.confirm_order_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlyburgerapp.data.OrderList

@Composable
fun ConfirmOrderListItem(
    order: OrderList,
) {

    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            order.burger?.onEach {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (it.burgerName == "FatBoyBurger" || it.burgerName == "OnlyBurger") {
                            it.burgerName
                        } else {
                            it.burgerName + "(${it.getSizeOfMeat()})"
                        },
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Serif,
                    )
                    Text(
                        text = it.price.toString(),
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.End,
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            order.drink?.onEach {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = it.name,
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Serif,
                    )
                    Text(
                        text = it.price.toString(),
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.End,
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            order.sauce?.onEach {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = it.name,
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Serif,
                    )
                    Text(
                        text = it.price.toString(),
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.End,
                    )
                }
            }
        }
    }
}