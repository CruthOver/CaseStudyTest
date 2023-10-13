package com.example.mobileapptest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import com.example.mobileapptest.helpers.SharedPrefs
import com.example.mobileapptest.modules.payment.HomePaymentActivity
import com.example.mobileapptest.modules.promo.PromoActivity

class MainActivity : ComponentActivity() {
    companion object {
        var prefs: SharedPrefs? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = SharedPrefs(this)
        if (prefs!!.userBalancePref <=0) {
            prefs!!.userBalancePref = 100000
        }
        setContent {
            UIGenerate()
        }
    }
}

@Composable
fun UIGenerate() {
    val mContext = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxHeight().fillMaxWidth().background(Color.White)
    ) {
        Button(onClick = {
            Intent(mContext, HomePaymentActivity::class.java).also {
                mContext.startActivity(it)
            }
        }) {
            Text("Pembayaran")
        }
        Button(onClick = {
            Intent(mContext, PromoActivity::class.java).also {
                mContext.startActivity(it)
            }
        }) {
            Text("Promo")
        }
        Button(onClick = { /*TODO*/ }) {
            Text("Portofolio")
        }
    }
}
