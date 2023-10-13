package com.example.mobileapptest.data

import com.example.mobileapptest.models.Promo
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("/promos")
    suspend fun getPromos(): List<Promo>
}