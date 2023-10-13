package com.example.mobileapptest.modules.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.mobileapptest.helpers.GlobalVar
import com.example.mobileapptest.helpers.SharedPrefs
import com.example.mobileapptest.viewmodel.PaymentViewModel

class ResultScannerActivity : AppCompatActivity() {
    private var prefs: SharedPrefs? = null

    private var bankName = ""
    private var transactionId = ""
    private var merchantName = ""
    private var totalPrice = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = SharedPrefs(this)
        val extras = intent.extras

        if (extras != null) {
            val result = extras.getString("result")
            val split = result?.split(".")
            bankName = split!![0]
            transactionId = split[1]
            merchantName = split[2]
            totalPrice = split[3]
        }
        setContent {
            DetailTransaction(bankName = bankName,
                transactionId = transactionId,
                merchantName = merchantName,
                totalPrice = totalPrice, onClickPay = { payNow() }, onClickDialog = { finish()})
        }
    }

    private fun payNow() {
        val currentBalance = prefs!!.userBalancePref
        if (currentBalance >= Integer.parseInt(totalPrice)) {
            val payment = HashMap<String, String>();
            payment["merchant_name"] = merchantName
            payment["total"] = totalPrice
            HomePaymentActivity.historyPayment.add(payment)
//            prefs!!.userPaymentPref = "$merchantName;$totalPrice;$transactionId"
            prefs!!.userBalancePref = currentBalance - Integer.parseInt(totalPrice)

            val paymentViewModel = PaymentViewModel()
            paymentViewModel.updateBalance(currentBalance - Integer.parseInt(totalPrice))
        } else {
            Toast.makeText(this, "Your balance is insufficient", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun DetailTransaction(bankName:String, transactionId:String, merchantName:String, totalPrice:String
                      , onClickPay:() -> Unit
, onClickDialog:() -> Unit) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
    ) {
        val openDialog = remember { mutableStateOf(false)  }
        Text(text = "Bank Name : $bankName")
        Text(text = "Transaction ID : $transactionId")
        Text(text = "Merchant Name : $merchantName")
        Text(text = "Total Price : $totalPrice")
        Button(onClick = {
            onClickPay()
            openDialog.value = true

        }) {
            Text(text = "Pay Now")
        }
        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                    onClickDialog()
                },
                title = {
                    Text(text = "Payment Successfully")
                },
                text = {
                    Text("Payment with Transaction ID : $transactionId has success")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                            onClickDialog()
                        }, Modifier.fillMaxWidth()) {
                        Text("OK")
                    }
                },
            )
        }
    }
}
