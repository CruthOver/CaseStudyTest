package com.example.mobileapptest.repository

import com.example.mobileapptest.data.ApiService
import com.example.mobileapptest.models.Promo

class PromoRepository(private val apiService: ApiService) {
    suspend fun getPromos(): List<Promo> {
        return apiService.getPromos()
    }

}