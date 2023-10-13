package com.example.mobileapptest.modules.promo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mobileapptest.R
import com.example.mobileapptest.models.Promo

class DetailPromoActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val promo = intent.getParcelableExtra<Promo>("promo")
        setContent {
            BuildDetail(promo)
        }
    }
}

@Composable
fun BuildDetail(promo: Promo?) {
    if (promo!= null) {
        Column(
            verticalArrangement = Arrangement.Top, modifier = Modifier.background(Color.White).fillMaxWidth().fillMaxHeight()){
            AsyncImage(model = promo.image?.formats?.medium?.url, contentDescription = "",
                modifier = Modifier.fillMaxWidth())
            Text(text = promo.name!!, modifier = Modifier.padding(16.dp))
            Text(text = promo.updatedAt!!, modifier = Modifier.padding(start =16.dp, end = 16.dp, bottom = 16.dp), fontSize = 12.sp, color = Color.Gray)
            Text(text = promo.desc!!, Modifier.padding(start =16.dp, end = 16.dp), fontSize = 12.sp)
        }
    }else {
        Column(Modifier.background(Color.White)) {
            Text(text = "Gagal Load Detail")
        }
    }
}