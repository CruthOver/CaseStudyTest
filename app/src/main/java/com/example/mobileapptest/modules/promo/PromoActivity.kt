package com.example.mobileapptest.modules.promo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mobileapptest.data.RetrofitClient
import com.example.mobileapptest.models.Promo
import com.example.mobileapptest.modules.payment.HomePaymentActivity
import com.example.mobileapptest.repository.PromoRepository
import com.example.mobileapptest.viewmodel.PromoViewModel

class PromoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val postRepository = PromoRepository(RetrofitClient().getService())
        val viewModel = PromoViewModel(postRepository, this)

        setContent{
            BuildListPromo(viewModel = viewModel)
        }
    }
}

@Composable
fun BuildListPromo(viewModel: PromoViewModel) {
    val promos by viewModel.promos.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.getPromos()
    }
    PromoList(promos = promos)
}

@Composable
fun PromoList(promos: List<Promo>) {
    Column(Modifier.background(Color.White)){
        if (promos.isEmpty()) {
            // Show loading indicator or placeholder
            Text(text = "Loading...")
        } else {
            LazyColumn (modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White), contentPadding = PaddingValues(16.dp)
            ){
                items(promos) { promo ->
                    ItemPromo(promo)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemPromo(promo:Promo) {
    val mContext = LocalContext.current
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
        ),
        onClick = {
            Intent(mContext, DetailPromoActivity::class.java).also {
                it.putExtra("promo", promo)
                mContext.startActivity(it)
            }
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Top,){
            AsyncImage(model = promo.image?.formats?.medium?.url, contentDescription = "",
                modifier = Modifier.fillMaxWidth().height(150.dp))
            Text(text = promo.name!!, modifier = Modifier.padding(16.dp))
            Text(text = promo.updatedAt!!, modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp), fontSize = 12.sp)
        }
    }
}