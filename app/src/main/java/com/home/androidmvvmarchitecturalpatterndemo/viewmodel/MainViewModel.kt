package com.home.androidmvvmarchitecturalpatterndemo.viewmodel

import androidx.lifecycle.ViewModel
import com.home.androidmvvmarchitecturalpatterndemo.model.repositories.MainRepository
import com.home.androidmvvmarchitecturalpatterndemo.utils.ApiException
import com.home.androidmvvmarchitecturalpatterndemo.utils.Coroutines
import com.home.androidmvvmarchitecturalpatterndemo.utils.NoInternetException
import com.home.androidmvvmarchitecturalpatterndemo.view.main.MainListener

class MainViewModel(val repository: MainRepository) : ViewModel() {

    var mainListener: MainListener? = null

    fun getMainCulturalAssetsResponse(limit: Int) {
        mainListener?.onStarted()
        Coroutines.main {
            try {
                val response = repository.getMainCulturalAssetsResponse(limit)
                response.let {
                    mainListener?.onSuccess(it.result.results)
                    repository.culturalAssets.postValue(it.result.results)
                    return@main
                }
            } catch (e: ApiException) {
                mainListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                mainListener?.onFailure(e.message!!)
            }
        }
    }
}