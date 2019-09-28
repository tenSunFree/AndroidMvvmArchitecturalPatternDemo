package com.home.androidmvvmarchitecturalpatterndemo.model.repositories

import androidx.lifecycle.MutableLiveData
import com.home.androidmvvmarchitecturalpatterndemo.model.network.MainApi
import com.home.androidmvvmarchitecturalpatterndemo.model.network.SafeApiRequest
import com.home.androidmvvmarchitecturalpatterndemo.model.network.responses.MainCulturalAssetsResponse

class MainRepository(private val api: MainApi) : SafeApiRequest() {

    val culturalAssets = MutableLiveData<List<MainCulturalAssetsResponse.Result.Results>>()
    val currentPosition = MutableLiveData<Int>()

    suspend fun getMainCulturalAssetsResponse(limit: Int): MainCulturalAssetsResponse {
        return apiRequest { api.getMainCulturalAssets(limit) }
    }
}