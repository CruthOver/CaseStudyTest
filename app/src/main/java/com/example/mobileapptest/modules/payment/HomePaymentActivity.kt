package com.example.mobileapptest.modules.payment

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobileapptest.helpers.SharedPrefs
import com.example.mobileapptest.viewmodel.PaymentViewModel
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class HomePaymentActivity : AppCompatActivity() {
    companion object {
        var prefs: SharedPrefs? = null
        var historyPayment = mutableListOf<HashMap<String, String>>()
    }

    private val paymentViewModel = PaymentViewModel()
    private var currentBalance = MutableLiveData(0)
    private val barcodeScanner = registerForActivityResult(ScanContract()) {
        result ->
        if (result.contents == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, ResultScannerActivity::class.java)
            intent.putExtra("result", result.contents)
            startActivity(intent)
//            Toast.makeText(this, result.contents, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showCamera() {
        val option = ScanOptions()
        option.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        option.setPrompt("Scan a QR Code")
        option.setCameraId(0)
        option.setBeepEnabled(false)
        option.setOrientationLocked(false)

        barcodeScanner.launch(option)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = SharedPrefs(this)
//        Toast.makeText(this, "ADSASDASDADSAd", Toast.LENGTH_SHORT).show()
        paymentViewModel.updateBalance(prefs!!.userBalancePref)

        setContent {
            ButtonOption(paymentViewModel, onClick = { checkPermissionCamera(this) }, onClickHistory= {goToHistory()})
        }
    }

    private val requestCameraPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){
                isGranted ->
            if (isGranted) {
                showCamera()
            } else {
                Toast.makeText(applicationContext, "Permission Not Granted", Toast.LENGTH_SHORT).show()
            }
        }

    private fun checkPermissionCamera(context: Context) {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)  {
            showCamera()
        } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
            Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            requestCameraPermission.launch(android.Manifest.permission.CAMERA)
        }
    }

    private fun goToHistory() {
        val intent = Intent(this, HistoryPaymentActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        paymentViewModel.updateBalance(prefs!!.userBalancePref)
    }
}

@Composable
fun ButtonOption(paymentViewModel: PaymentViewModel = viewModel(), onClick: ()-> Unit, onClickHistory: ()-> Unit) {
    val paymentViewState by paymentViewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Text(text = "Saldo anda : ${paymentViewState.balance}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Button(onClick = {
            onClick()
        }) {
            Text("Scan QR")
        }
        Button(onClick = { onClickHistory() }) {
            Text("History")
        }
    }
}