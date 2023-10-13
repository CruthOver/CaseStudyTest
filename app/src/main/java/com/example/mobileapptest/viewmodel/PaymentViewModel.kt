package com.example.mobileapptest.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mobileapptest.helpers.SharedPrefs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PaymentViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(PaymentViewState())
    val uiState: StateFlow<PaymentViewState> = _uiState.asStateFlow()

    var balance by mutableStateOf(0)

    fun updateBalance(balance: Int){
        _uiState.update { currentState->
            currentState.copy(
                balance = balance
            )
        }
    }
}

data class PaymentViewState(
    val balance: Int = 0,
)