package com.example.mobileapptest.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileapptest.models.Promo
import com.example.mobileapptest.repository.PromoRepository
import kotlinx.coroutines.launch

class PromoViewModel(private val promoRepository: PromoRepository, private val context: Context): ViewModel() {
    private val _promos = MutableLiveData<List<Promo>>()
    val promos: LiveData<List<Promo>> = _promos

    suspend fun getPromos() {
        viewModelScope.launch {
            try {
                val promo = promoRepository.getPromos()
                _promos.value = promo
            } catch (e: Exception) {
                // Handle error
                Toast.makeText(context, "${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}