package com.mertoenjosh.triviaquestadmin.data.network.util

import com.mertoenjosh.triviaquestadmin.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceBuilder {
    companion object {
        private val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        private val retrofit: Retrofit = builder.build()

        fun <s> buildService(serviceType: Class<s>): s {
            return retrofit.create(serviceType)
        }
    }
}
