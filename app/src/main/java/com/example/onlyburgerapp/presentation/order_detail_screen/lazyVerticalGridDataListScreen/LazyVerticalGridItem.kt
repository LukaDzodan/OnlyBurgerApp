package com.example.onlyburgerapp.presentation.order_detail_screen.lazyVerticalGridDataListScreen

import androidx.compose.foundation.clickable
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

@Composable
fun LazyVerticalGridItem(
    text : String
) {
    Text(
        modifier = Modifier
            .padding(8.dp),
        text = text,
        color = Color.White,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center,
    )
}