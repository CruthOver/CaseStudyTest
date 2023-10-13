package com.example.mobileapptest.modules.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mobileapptest.helpers.GlobalVar

class HistoryPaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "${HomePaymentActivity.historyPayment.size}", Toast.LENGTH_SHORT).show()
        setContent{
            BuildList()
        }
    }
}

@Composable
fun BuildList() {
    LazyColumn(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .background(Color.White), contentPadding = PaddingValues(16.dp)
    ){
        items(HomePaymentActivity.historyPayment){
            payment ->
            ItemCard(merchantName = payment["merchant_name"]!!, total = payment["total"]!!)
            Column{

            }
        }
    }
}

@Composable
fun ItemCard(merchantName: String, total: String) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        colors =  CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )

    ) {
        Column(Modifier.padding(16.dp)){
            Text("Merchant Name : $merchantName")
            Text("Total : $total")
        }
    }
}