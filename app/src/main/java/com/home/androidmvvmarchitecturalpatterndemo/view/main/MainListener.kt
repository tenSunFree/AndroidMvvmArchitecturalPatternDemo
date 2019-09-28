package com.home.androidmvvmarchitecturalpatterndemo.view.main

import com.home.androidmvvmarchitecturalpatterndemo.model.network.responses.MainCulturalAssetsResponse

interface MainListener {
    fun onStarted()
    fun onSuccess(results: List<MainCulturalAssetsResponse.Result.Results>)
    fun onFailure(message: String)
}