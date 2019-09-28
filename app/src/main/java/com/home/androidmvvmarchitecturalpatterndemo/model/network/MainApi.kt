package com.home.androidmvvmarchitecturalpatterndemo.model.network

import com.home.androidmvvmarchitecturalpatterndemo.model.network.responses.MainCulturalAssetsResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    @GET("https://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=d40ee29c-a538-4a87-84f0-f43acfa19a20")
    suspend fun getMainCulturalAssets(@Query("limit") limit: Int)
            : Response<MainCulturalAssetsResponse>

    companion object {
        private const val baseUrl = "https://data.taipei/opendata/datalist/"
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): MainApi {
            val okHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpclient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MainApi::class.java)
        }
    }
}

