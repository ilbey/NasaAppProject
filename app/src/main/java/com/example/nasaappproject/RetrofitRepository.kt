package com.example.nasaappproject

class RetrofitRepository {
    var retrofitClient : RetrofitService = RetrofitClient().retrofitService
    suspend fun getData() = retrofitClient.getMarsData()
}