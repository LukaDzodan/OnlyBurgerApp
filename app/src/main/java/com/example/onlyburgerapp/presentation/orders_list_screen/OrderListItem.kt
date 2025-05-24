package com.example.onlyburgerapp.presentation.orders_list_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlyburgerapp.R.drawable.order_list_item_background_picture
import com.example.onlyburgerapp.data.OrderList
import com.example.onlyburgerapp.ui.theme.OnlyBurgerOrange


@Composable
fun OrderListItem(
    orderList: OrderList,
    onEvent: (OrdersListEvent) -> Unit,
    modifier: Modifier = Modifier,
) {

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .height(160.dp)
            .padding(8.dp)
            .background(Color.Transparent)
            .fillMaxWidth()
            .clickable {
                onEvent(OrdersListEvent.OnOrderItemClick(orderList))
            },
    ) {

        //Trebaju ti converteri za burgere,sokove i soseve

        Box(modifier = Modifier.fillMaxSize()){
            Image(
                painter = painterResource(id = order_list_item_background_picture),
                contentDescription = "Order Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )


            Text(
                text = orderList.adress ?: "",
                color = OnlyBurgerOrange,
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 25.sp, lineHeight = 18.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.TopCenter).padding(top = 20.dp).background(Color.Black)
            )

            Text(
                text = orderList.sumPrice.toString(),
                color = OnlyBurgerOrange,
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 20.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 20.dp).background(Color.Black)
            )

            IconButton(
                onClick = { onEvent(OrdersListEvent.DeleteOrder(orderList)) },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .background(OnlyBurgerOrange)
                    .clip(RoundedCornerShape(50))
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete order",
                    tint = Color.Black,
                    modifier = Modifier.size(50.dp)
                )
            }
        }


    }

}