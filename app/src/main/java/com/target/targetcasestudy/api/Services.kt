package com.target.targetcasestudy.api

import android.util.Log
import com.target.targetcasestudy.api.response.ApiResponse
import com.target.targetcasestudy.api.response.Product
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface Services {

    @GET("deals")
    suspend fun getProducts(
    ):Response<ApiResponse>

    @GET("deals/{id}")
    suspend fun getProduct(
        @Path("id") prdtId:Int
    ):Response<Product>


    companion object{
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ):Services{
            var requestInterceptor = Interceptor{chain ->
                val url = chain.request()
                    .url
                    .newBuilder()
                    .build()
                Log.d("(Saquib)","the url is "+url.toString())
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.target.com/mobile_case_study_deals/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Services::class.java)
        }
    }
}