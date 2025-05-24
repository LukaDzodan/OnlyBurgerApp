package com.example.onlyburgerapp.presentation.order_detail_screen.popUpScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlyburgerapp.domain.model.BurgerSize

@Composable
fun PopUpBurgerItem(
    optionValue : (BurgerSize) -> Unit
){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Single",
                color = Color.Black,
                fontSize = 40.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .border(BorderStroke(2.dp,Color.Black), RoundedCornerShape(10.dp))
                    .padding(8.dp)
                    .clickable{
                        optionValue(BurgerSize.Single)
                    }
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Double",
                color = Color.Black,
                fontSize = 40.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .border(BorderStroke(2.dp,Color.Black), RoundedCornerShape(10.dp))
                    .padding(8.dp)
                    .clickable{
                        optionValue(BurgerSize.Double)
                    }
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Triple",
                color = Color.Black,
                fontSize = 40.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .border(BorderStroke(2.dp,Color.Black), RoundedCornerShape(10.dp))
                    .padding(8.dp)
                    .clickable{
                        optionValue(BurgerSize.Tripple)
                    }
            )
        }
    }
}